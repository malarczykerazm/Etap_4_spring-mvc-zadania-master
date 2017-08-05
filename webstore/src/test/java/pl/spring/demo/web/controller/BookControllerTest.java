package pl.spring.demo.web.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.controller.BookController;
import pl.spring.demo.enumerations.BookStatus;
import pl.spring.demo.service.BookService;
import pl.spring.demo.service.impl.BookValidationServiceImpl;
import pl.spring.demo.to.BookTo;

@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

	private List<BookTo> allBooks;

	private MockMvc mockMvc;

	private final BookTo book1 = new BookTo();
	private final BookTo book2 = new BookTo();

	@Mock
	private BookService bookService;

	@Mock
	private BookValidationServiceImpl bookValidation;

	@InjectMocks
	private BookController bookController;

	@Before
	public void setup() {
		allBooks = new ArrayList<BookTo>();
		book1.setId(1L);
		book1.setTitle("title1");
		book1.setAuthors("author1");
		book1.setStatus(BookStatus.FREE);
		allBooks.add(book1);
		book2.setId(2L);
		book2.setTitle("title2");
		book2.setAuthors("author2");
		book2.setStatus(BookStatus.MISSING);
		allBooks.add(book2);

		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/templates/");
		viewResolver.setSuffix(".html");

		mockMvc = MockMvcBuilders.standaloneSetup(bookController).setViewResolvers(viewResolver).build();
	}

	@Test
	public void shouldReturnListOfAllBooks() throws Exception {

		// given
		given(bookService.findAllBooks()).willReturn(allBooks);

		// when
		ResultActions resultActions = mockMvc.perform(get("/books"));

		// then
		resultActions.andExpect(view().name("books")).andExpect(model().attribute(ModelConstants.BOOK_LIST, allBooks));
		Mockito.verify(bookService, Mockito.times(1)).findAllBooks();
	}

	@Test
	public void shouldReturnOneBookById() throws Exception {

		// given
		Long id = 1L;
		Mockito.doNothing().when(bookValidation).validateIdOfBook(id);
		given(bookService.findBookById(id)).willReturn(book1);

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/book?id=" + id));

		// then
		resultActions.andExpect(view().name("bookDetails")).andExpect(model().attribute(ModelConstants.BOOK, book1));
		Mockito.verify(bookValidation, Mockito.times(1)).validateIdOfBook(id);
		Mockito.verify(bookService, Mockito.times(1)).findBookById(id);
		Mockito.inOrder(bookValidation, bookService);
	}

	@Test
	public void shouldGetNewBookInfoToAddOneBook() throws Exception {

		// given

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/add"));

		// then
		resultActions.andExpect(view().name("addBook")).andExpect(model().attribute("newBook", new BookTo()));
	}

	@Test
	public void shouldAddOneBook() throws Exception {

		// given
		Long newId = 3L;

		BookTo newBook = new BookTo();
		newBook.setTitle("title");
		newBook.setAuthors("author");
		newBook.setStatus(BookStatus.FREE);

		Mockito.doNothing().when(bookValidation).valideteBookData(newBook);
		given(bookService.saveBook(newBook)).willReturn(newBook);
		given(bookService.getHighestId()).willReturn(newId);
		given(bookService.findBookById(newId)).willReturn(newBook);

		// when
		ResultActions resultActions = mockMvc.perform(post("/books/add").flashAttr("newBook", newBook));

		// then
		resultActions.andExpect(view().name("bookJustAdded")).andExpect(model().attribute("book", newBook));
		Mockito.verify(bookValidation, Mockito.times(1)).valideteBookData(newBook);
		Mockito.verify(bookService, Mockito.times(1)).saveBook(newBook);
		Mockito.verify(bookService, Mockito.times(1)).getHighestId();
		Mockito.verify(bookService, Mockito.times(1)).findBookById(newId);
		Mockito.inOrder(bookValidation, bookService, bookService, bookService);
	}

	@Test
	public void shouldGetNewBookInfoToFindMatchingBooks() throws Exception {

		// given

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/find"));

		// then
		resultActions.andExpect(view().name("findBooks")).andExpect(model().attribute("paramsOfBook", new BookTo()));
	}

	@Test
	public void shouldFindTwoMatchingBooks() throws Exception {

		// given
		BookTo searchingParams = new BookTo();
		searchingParams.setTitle("title");
		searchingParams.setAuthors("");

		List<BookTo> books = new ArrayList<BookTo>();
		books.add(book1);
		books.add(book2);

		given(bookService.findBooksByTitleAndAuthor(searchingParams.getTitle(), searchingParams.getAuthors()))
				.willReturn(books);

		// when
		ResultActions resultActions = mockMvc.perform(post("/books/find").flashAttr("paramsOfBook", searchingParams));

		// then
		resultActions.andExpect(view().name("billOfFoundBooks")).andExpect(model().attribute("bookList", books));
		Mockito.verify(bookService, Mockito.times(1)).findBooksByTitleAndAuthor(searchingParams.getTitle(),
				searchingParams.getAuthors());
	}

	@Test
	public void shouldFindOneMatchingBooks() throws Exception {

		// given
		BookTo searchingParams = new BookTo();
		searchingParams.setTitle("title");
		searchingParams.setAuthors("");
		searchingParams.setStatus(BookStatus.FREE);

		String jsonSearchingParams = new ObjectMapper().writeValueAsString(searchingParams);

		List<BookTo> books = new ArrayList<BookTo>();
		books.add(book1);

		given(bookService.findBooksByTitleAndAuthor(searchingParams.getTitle(), searchingParams.getAuthors()))
				.willReturn(books);

		// when
		ResultActions resultActions = mockMvc.perform(post("/books/find").flashAttr("paramsOfBook", searchingParams)
				.contentType(MediaType.APPLICATION_JSON).content(jsonSearchingParams));

		// then
		resultActions.andExpect(view().name("billOfFoundBooks")).andExpect(model().attribute("bookList", books));
		Mockito.verify(bookService, Mockito.times(1)).findBooksByTitleAndAuthor(searchingParams.getTitle(),
				searchingParams.getAuthors());
	}

	@Test
	public void shouldReturnListOfAllBooksWithDeleteOption() throws Exception {

		// given
		given(bookService.findAllBooks()).willReturn(allBooks);

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/delete"));

		// then
		resultActions.andExpect(view().name("booksWithDeleteOption"))
				.andExpect(model().attribute(ModelConstants.BOOK_LIST, allBooks));
		Mockito.verify(bookService, Mockito.times(1)).findAllBooks();
	}

	@Test
	public void shouldDeleteOneBookAndRturnItsDetails() throws Exception {

		// given
		Long id = 1L;
		Mockito.doNothing().when(bookValidation).validateIdOfBook(id);
		given(bookService.findBookById(id)).willReturn(book1);
		Mockito.doNothing().when(bookService).deleteBook(id);

		// when
		ResultActions resultActions = mockMvc.perform(get("/books/deletedBook?id=" + id));

		// then
		resultActions.andExpect(view().name("bookJustDeleted"))
				.andExpect(model().attribute(ModelConstants.BOOK, book1));
		Mockito.verify(bookValidation, Mockito.times(1)).validateIdOfBook(id);
		Mockito.verify(bookService, Mockito.times(1)).findBookById(id);
		Mockito.verify(bookService, Mockito.times(1)).deleteBook(id);
		Mockito.inOrder(bookValidation, bookService, bookService);
	}

}
