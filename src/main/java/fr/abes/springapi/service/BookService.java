package fr.abes.springapi.service;

import fr.abes.springapi.data.BookDAO;
import fr.abes.springapi.exception.BookNotFoundException;
import fr.abes.springapi.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDAO bookDAO;

    public List<Book> getBookList(){
        return bookDAO.findAll();
    }

    public Book getBookById(Long id){
        return bookDAO.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book saveBook(Book book){
        return bookDAO.save(book);
    }

    public Book updateBook(Book newBook, Long id){
        return bookDAO.findById(id)
                .map(book -> {
                    book.setAuthor(newBook.getAuthor());
                    book.setTitle(newBook.getTitle());
                    book.setDescription(newBook.getDescription());
                    return bookDAO.save(book);
                }).orElseGet(() -> {
                    newBook.setId(id);
                    return bookDAO.save(newBook);
                });
    }

    public void deleteBook(Long id){
        bookDAO.deleteById(id);
    }
}
