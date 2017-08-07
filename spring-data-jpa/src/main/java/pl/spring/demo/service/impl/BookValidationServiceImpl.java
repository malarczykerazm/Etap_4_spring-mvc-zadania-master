package pl.spring.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.spring.demo.enumerations.BookStatus;
import pl.spring.demo.exceptions.NoSuchBookIdException;
import pl.spring.demo.exceptions.NotEnoughBookDataException;
import pl.spring.demo.service.BookService;
import pl.spring.demo.service.BookValidationService;
import pl.spring.demo.to.BookTo;

@Service
public class BookValidationServiceImpl implements BookValidationService {

	@Autowired
	BookService bookService;

	@Override
	public void validateBookId(BookTo foundBook) throws NoSuchBookIdException {
		if (null == foundBook) {
			throw new NoSuchBookIdException("There is no book of the provided Id.");
		}
	}

	@Override
	public void valideteBookData(BookTo book) throws NotEnoughBookDataException {
		if ("" == book.getTitle() || null == book.getTitle()) {
			throw new NotEnoughBookDataException("There was no book title provided.");
		}
		if ("" == book.getAuthors() || null == book.getAuthors()) {
			throw new NotEnoughBookDataException("There was no book author(s) provided.");
		}
		if (BookStatus.FREE != book.getStatus() && BookStatus.LOAN != book.getStatus()
				&& BookStatus.MISSING != book.getStatus()) {
			throw new NotEnoughBookDataException("There was no legal book status provided.");
		}

	}

}
