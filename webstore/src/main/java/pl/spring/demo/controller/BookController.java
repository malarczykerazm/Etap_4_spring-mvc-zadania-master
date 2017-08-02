package pl.spring.demo.controller;

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
import pl.spring.demo.service.BookService;
import pl.spring.demo.to.BookTo;

/**
 * Book controller
 * 
 * @author mmotowid
 *
 */
@Controller
@RequestMapping(value = "/books")
public class BookController {

	@Autowired
	private BookService bookService;

	// @RequestMapping
	// public String list(Model model) {
	// // TODO: implement default method
	// return ViewNames.BOOKS;
	// }

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
		modelAndView.addObject(ModelConstants.BOOK, bookService.findAllBooks().stream().filter(b -> (b.getId() == id)).findFirst().orElse(null));
		modelAndView.setViewName(ViewNames.BOOK);
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
	 * Method adds a new book to database with attributes provided
	 * by the GET method
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addNewBook(@ModelAttribute("newBook") BookTo book) {
		bookService.saveBook(book);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(ModelConstants.BOOK, bookService.findAllBooks().stream().filter(b -> (b.getId() == bookService.findAllBooks().size())).findFirst().orElse(null));
		modelAndView.setViewName(ViewNames.BOOK_JUST_ADDED);
		return modelAndView;
	}
	
	/**
	 * Method starts a form, that allows to provide new book searching parameters
	 */
	@RequestMapping(value = "/find", method = RequestMethod.GET)
	public ModelAndView findBook() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("paramsOfBook", new BookTo());
		modelAndView.setViewName(ViewNames.FIND_BOOKS);
		return modelAndView;
	}
	
	/**
	 * Method returns results of book searching with attributes provided
	 * by the POST method
	 */
	@RequestMapping(value = "/find", method = RequestMethod.POST)
	public ModelAndView findBook(@ModelAttribute("paramsOfBook") BookTo paramsOfBook) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject(ModelConstants.BOOK_LIST, bookService.findAllBooks().stream().filter(b -> (b.getTitle().contains(paramsOfBook.getTitle()))).collect(Collectors.toList()));
		modelAndView.setViewName(ViewNames.BILL_OF_FOUND_BOOKS);
		return modelAndView;
	}

	// TODO: here implement methods which displays book info based on query
	// arguments

	// TODO: Implement GET / POST methods for "add book" functionality

	/**
	 * Binder initialization
	 */
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("id", "title", "authors", "status");
	}

}
