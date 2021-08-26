package fr.abes.springapi.controller;

import fr.abes.springapi.dto.BookDto;
import fr.abes.springapi.exception.BookNotFoundException;
import fr.abes.springapi.model.Book;
import fr.abes.springapi.service.BookService;
import fr.abes.springapi.utilitaire.DtoMapperUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class BookRestController {

    @Autowired
    private BookService bookService;

    @Autowired
    private DtoMapperUtility dtoMapper;

    @GetMapping("/api/getBooks")
    public List<BookDto> getBookList(){
        List<Book> books= bookService.getBookList();
        return dtoMapper.mapList(books, BookDto.class);
    }

    @GetMapping("/api/getBook/{id}")
    public BookDto getBookById(@PathVariable Long id){
        Book book = bookService.getBookById(id);
        return dtoMapper.map(book, BookDto.class);
    }

    @GetMapping("/api/getBooksByKeyword/{keyword}")
    public List<BookDto> getBooksByKeyword(@PathVariable String keyword){
        List<Book> books= bookService.getBookListByKeyword(keyword);
        return dtoMapper.mapList(books, BookDto.class);
    }

    @PostMapping("/api/postBook")
    public BookDto saveBook(@RequestBody BookDto bookDto){
        Book book = dtoMapper.map(bookDto, Book.class);
        Book savedBook = bookService.saveBook(book);
        return dtoMapper.map(savedBook, BookDto.class);
    }

    @PutMapping("/api/updateBook/{id}")
    public BookDto updateBook(@RequestBody BookDto newBookDto, @PathVariable Long id){
        Book newBook = dtoMapper.map(newBookDto, Book.class);
        Book updatedBook = bookService.updateBook(newBook,id);
        return dtoMapper.map(updatedBook, BookDto.class);
    }

    @DeleteMapping("/api/deleteBook/{id}")
    public void deleteBook(@PathVariable Long id){
        bookService.deleteBook(id);
    }
}
