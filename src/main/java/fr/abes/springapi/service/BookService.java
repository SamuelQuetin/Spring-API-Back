package fr.abes.springapi.service;

import fr.abes.springapi.data.IBookDAO;
import fr.abes.springapi.exception.BookNotFoundException;
import fr.abes.springapi.model.Book;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private IBookDAO IBookDAO;

    public List<Book> getBookList(){
        return IBookDAO.findAll();
    }

    public Book getBookById(Long id){
        return IBookDAO.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public Book saveBook(Book book){
        return IBookDAO.save(book);
    }

    public Book updateBook(Book newBook, Long id){
        return IBookDAO.findById(id)
                .map(book -> {
                    book.setAuthor(newBook.getAuthor());
                    book.setTitle(newBook.getTitle());
                    book.setDescription(newBook.getDescription());
                    return IBookDAO.save(book);
                }).orElseGet(() -> {
                    newBook.setId(id);
                    return IBookDAO.save(newBook);
                });
    }

    public void deleteBook(Long id){
        IBookDAO.deleteById(id);
    }
}
