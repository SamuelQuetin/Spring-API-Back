package fr.abes.springapi.controller;

import fr.abes.springapi.data.BookDAO;
import fr.abes.springapi.exception.BookNotFoundException;
import fr.abes.springapi.model.Book;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class BookRestController {

    @Autowired
    private BookDAO bookDAO;

    @GetMapping("/api/getBooks")
    public List<Book> getBookList(){
        return bookDAO.findAll();
    }

    @GetMapping("/api/getBook/{id}")
    public Book getBookById(@PathVariable Long id){
        return bookDAO.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    @PostMapping("/api/postBook")
    public Book saveBook(@RequestBody Book book){
        return bookDAO.save(book);
    }

    @PutMapping("/api/updateBook/{id}")
    public Book updateBook(@RequestBody Book newBook, @PathVariable Long id){
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

    @DeleteMapping("/api/deleteBook/{id}")
    public void deleteBook(@PathVariable Long id){
        bookDAO.deleteById(id);
    }
}
