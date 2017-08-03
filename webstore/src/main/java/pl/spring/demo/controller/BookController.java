package pl.spring.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;
import pl.spring.demo.enumerations.BookStatus;
import pl.spring.demo.exceptions.NoSuchBookIdException;
import pl.spring.demo.exceptions.NotEnoughBookDataException;
import pl.spring.demo.service.BookService;
import pl.spring.demo.service.BookValidationService;
import pl.spring.demo.to.BookTo;

/**
 * 
 * @author EMALARCZ
 *
 */
@Controller
@RequestMapping(value = "/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@Autowired
	private BookValidationService bookValidation;

	/**
	 * Method collects info about all books
	 */
	@RequestMapping
	public ModelAndView allBooks() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(ModelConstants.BOOK_LIST, bookService.findAllBooks());
		modelAndView.setViewName(ViewNames.BOOKS);
		return modelAndView;
	}

	/**
	 * Method collects info about the book of the particular ID
	 */
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public ModelAndView oneBookById(@RequestParam("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			bookValidation.validateIdOfBook(id);
		} catch (NoSuchBookIdException e) {
			modelAndView.addObject(ModelConstants.ERROR_MESSAGE, e.getMessage());
			modelAndView.setViewName(ViewNames._404);
			return modelAndView;
		}

		modelAndView.addObject(ModelConstants.BOOK,
				bookService.findAllBooks().stream().filter(b -> (b.getId() == id)).findFirst().orElse(null));
		modelAndView.setViewName(ViewNames.BOOK_DETAILS);
		return modelAndView;
	}

	/**
	 * Method starts a form, that allows to provide new book data
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addNewBook() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("newBook", new BookTo());
		modelAndView.setViewName(ViewNames.ADD_BOOK);
		return modelAndView;
	}

	/**
	 * Method adds a new book to database with attributes provided by the GET
	 * method
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addNewBook(@ModelAttribute("newBook") BookTo book) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			bookValidation.valideteBookData(book);
		} catch (NotEnoughBookDataException e) {
			modelAndView.addObject(ModelConstants.ERROR_MESSAGE, e.getMessage());
			modelAndView.setViewName(ViewNames._404);
			return modelAndView;
		}

		bookService.saveBook(book);
		modelAndView.addObject(ModelConstants.BOOK, bookService.findBookById(bookService.getHighestId()));
		modelAndView.setViewName(ViewNames.BOOK_JUST_ADDED);
		return modelAndView;
	}

	/**
	 * Method starts a form, that allows to provide new book searching
	 * parameters
	 */
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView findBook() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("paramsOfBook", new BookTo());
		modelAndView.setViewName(ViewNames.FIND_BOOKS);
		return modelAndView;
	}

	/**
	 * Method returns results of book searching with attributes provided by the
	 * POST method
	 */
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView findBook(@ModelAttribute("paramsOfBook") BookTo paramsOfBook) {

		List<BookTo> billOfFoundBooks = bookService.findBooksByTitleAndAuthor(paramsOfBook.getTitle(),
				paramsOfBook.getAuthors());

		if (paramsOfBook.getStatus() == BookStatus.FREE) {
			billOfFoundBooks = billOfFoundBooks.stream().filter(b -> (b.getStatus() == BookStatus.FREE))
					.collect(Collectors.toList());
		}

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(ModelConstants.BOOK_LIST, billOfFoundBooks);
		modelAndView.setViewName(ViewNames.BILL_OF_FOUND_BOOKS);
		return modelAndView;
	}

	/**
	 * Method shows a list o books with added option DELETE next to every
	 * position on the list
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteBook() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(ModelConstants.BOOK_LIST, bookService.findAllBooks());
		modelAndView.setViewName(ViewNames.BOOKS_WITH_DELETE_OPTION);
		return modelAndView;
	}

	/**
	 * Method deletes a chosen book from database and shows a proper book info
	 */
	@RequestMapping(value = "/deletedBook", method = RequestMethod.GET)
	public ModelAndView deleteBook(@RequestParam("id") Long id) {
		ModelAndView modelAndView = new ModelAndView();

		try {
			bookValidation.validateIdOfBook(id);
		} catch (NoSuchBookIdException e) {
			modelAndView.addObject(ModelConstants.ERROR_MESSAGE, e.getMessage());
			modelAndView.setViewName(ViewNames._404);
			return modelAndView;
		}

		modelAndView.addObject(ModelConstants.BOOK, bookService.findBookById(id));
		modelAndView.setViewName(ViewNames.BOOK_JUST_DELETED);
		bookService.deleteBook(id);
		return modelAndView;
	}

	/**
	 * Binder initialization
	 */
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "title", "authors", "status");
	}

}
