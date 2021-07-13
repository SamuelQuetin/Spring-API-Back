package fr.abes.springapi.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id) {
        super("Could not find the book " + id);
    }
}
