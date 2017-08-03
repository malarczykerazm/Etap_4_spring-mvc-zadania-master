package pl.spring.demo.service;

import pl.spring.demo.exceptions.NoSuchBookIdException;
import pl.spring.demo.exceptions.NotEnoughBookDataException;
import pl.spring.demo.to.BookTo;

public interface BookValidationService {

	void validateIdOfBook(Long id) throws NoSuchBookIdException;

	void valideteBookData(BookTo book) throws NotEnoughBookDataException;

}
