package fr.abes.springapi.controller;

import fr.abes.springapi.data.BookDAO;
import fr.abes.springapi.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class BookRestController {

    @Autowired
    private BookDAO bookDAO;

    @GetMapping("/api/getBooks")
    public List<Book> bookList(){
        return bookDAO.findAll();
    }

    @PostMapping("/api/postBook")
    public void saveBook(@RequestBody Book book){
        bookDAO.save(book);
    }
}
