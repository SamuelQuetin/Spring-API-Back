package fr.abes.springapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.abes.springapi.controller.BookRestController;
import fr.abes.springapi.model.Book;
import fr.abes.springapi.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = SpringApiApplication.class)
@AutoConfigureMockMvc
class BookRestControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private BookRestController bookRestController;

    private List<Book> bookList;

    private Book bookToPost;
    private Book bookToPut;

    @BeforeEach
    public void setUp() {

        Book test = new Book("test", "test", "test");
        Book test2 = new Book("test2", "test2", "test2");
        Book toto = new Book("toto", "toto", "toto");
        bookList = new ArrayList<>();
        bookList.add(test);
        bookList.add(test2);
        bookList.add(toto);
        bookToPut = new Book("newAuthor", "newTitle", "newDescription");
        bookToPost = new Book("author", "title", "description");
        bookToPost.setId(5L);

    }

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(bookRestController);
    }

    @Test
    public void homeGetMethod() throws Exception {
        mvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void homePostMethod() throws Exception {
        mvc.perform(post("/"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void homePutMethod() throws Exception {
        mvc.perform(put("/"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void homeDeleteMethod() throws Exception {
        mvc.perform(delete("/"))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void getBooksGetMethod() throws Exception {
        when(bookService.getBookList()).thenReturn(bookList);
        mvc.perform(get("/api/getBooks"))
                .andExpect(status().isOk());
    }

    //TODO : checker le resultat
    @Test
    public void getBooksPutMethod() throws Exception {
        when(bookService.getBookList()).thenReturn(bookList);
        mvc.perform(put("/api/getBooks"))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void getBooksPostMethod() throws Exception {
        when(bookService.getBookList()).thenReturn(bookList);
        mvc.perform(post("/api/getBooks"))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void getBooksDeleteMethod() throws Exception {
        when(bookService.getBookList()).thenReturn(bookList);
        mvc.perform(delete("/api/getBooks"))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void getBookGetMethod() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookList.get(0));
        mvc.perform(get("/api/getBook/1"))
                .andExpect(status().isOk());
    }

    //TODO : checker le resultat
    @Test
    public void getBookPutMethod() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookList.get(0));
        mvc.perform(put("/api/getBook/1"))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void getBookPostMethod() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookList.get(0));
        mvc.perform(post("/api/getBook/1"))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void getBookDeleteMethod() throws Exception {
        when(bookService.getBookById(1L)).thenReturn(bookList.get(0));
        mvc.perform(delete("/api/getBook/1"))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void postBookGetMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPost);
        when(bookService.saveBook(bookToPost)).thenReturn(bookToPost);
        mvc.perform(get("/api/postBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void postBookPutMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPost);
        when(bookService.saveBook(bookToPost)).thenReturn(bookToPost);
        mvc.perform(put("/api/postBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void postBookPostMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPost);
        when(bookService.saveBook(bookToPost)).thenReturn(bookToPost);
        mvc.perform(post("/api/postBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    //TODO : checker le resultat
    @Test
    public void postBookDeleteMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPost);
        when(bookService.saveBook(bookToPost)).thenReturn(bookToPost);
        mvc.perform(delete("/api/postBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void deleteBookGetMethod() throws Exception {
        doNothing().when(bookService).deleteBook(1L);
        mvc.perform(get("/api/deleteBook/1"))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void deleteBookPutMethod() throws Exception {
        doNothing().when(bookService).deleteBook(1L);
        mvc.perform(put("/api/deleteBook/1"))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void deleteBookPostMethod() throws Exception {
        doNothing().when(bookService).deleteBook(1L);
        mvc.perform(post("/api/deleteBook/1"))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void deleteBookDeleteMethod() throws Exception {
        doNothing().when(bookService).deleteBook(1L);
        mvc.perform(delete("/api/deleteBook/1"))
                .andExpect(status().isOk());
    }

    //TODO : checker le resultat
    @Test
    public void updateBookGetMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPut);
        when(bookService.updateBook(bookToPut,1L)).thenReturn(bookToPut);
        mvc.perform(get("/api/updateBook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void updateBookPutMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPut);
        when(bookService.updateBook(bookToPut,1L)).thenReturn(bookToPut);
        mvc.perform(put("/api/updateBook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    //TODO : checker le resultat
    @Test
    public void updateBookPostMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPut);
        when(bookService.updateBook(bookToPut,1L)).thenReturn(bookToPut);
        mvc.perform(post("/api/updateBook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isMethodNotAllowed());
    }

    //TODO : checker le resultat
    @Test
    public void updateBookDeleteMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPut);
        when(bookService.updateBook(bookToPut,1L)).thenReturn(bookToPut);
        mvc.perform(delete("/api/updateBook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isMethodNotAllowed());
    }

}
