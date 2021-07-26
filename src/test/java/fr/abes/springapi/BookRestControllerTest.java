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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

    @Test
    public void wrongHomeGetMethod() throws Exception {
        mvc.perform(get("/test"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void getBooksGetMethod() throws Exception {
        when(bookService.getBookList()).thenReturn(bookList);
        mvc.perform(get("/api/getBooks"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].author").value(bookList.get(0).getAuthor()))
                .andExpect(jsonPath("$[0].title").value(bookList.get(0).getTitle()))
                .andExpect(jsonPath("$[0].description").value(bookList.get(0).getDescription()))
                .andExpect(jsonPath("$[1].author").value(bookList.get(1).getAuthor()))
                .andExpect(jsonPath("$[1].title").value(bookList.get(1).getTitle()))
                .andExpect(jsonPath("$[1].description").value(bookList.get(1).getDescription()))
                .andExpect(jsonPath("$[2].author").value(bookList.get(2).getAuthor()))
                .andExpect(jsonPath("$[2].title").value(bookList.get(2).getTitle()))
                .andExpect(jsonPath("$[2].description").value(bookList.get(2).getDescription()));
    }

    @Test
    public void getBooksPutMethod() throws Exception {
        mvc.perform(put("/api/getBooks"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void getBooksPostMethod() throws Exception {
        mvc.perform(post("/api/getBooks"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void getBooksDeleteMethod() throws Exception {
        mvc.perform(delete("/api/getBooks"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void getBookGetMethod() throws Exception {
        when(bookService.getBookById(0L)).thenReturn(bookList.get(0));
        mvc.perform(get("/api/getBook/0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value(bookList.get(0).getAuthor()))
                .andExpect(jsonPath("$.title").value(bookList.get(0).getTitle()))
                .andExpect(jsonPath("$.description").value(bookList.get(0).getDescription()));
    }

    @Test
    public void getBookPutMethod() throws Exception {
        mvc.perform(put("/api/getBook/0"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void getBookPostMethod() throws Exception {
        mvc.perform(post("/api/getBook/0"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void getBookDeleteMethod() throws Exception {
        mvc.perform(delete("/api/getBook/0"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void postBookGetMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPost);
        mvc.perform(get("/api/postBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void postBookPutMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPost);
        mvc.perform(put("/api/postBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isMethodNotAllowed());
    }

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

    @Test
    public void postBookDeleteMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPost);
        mvc.perform(delete("/api/postBook")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void deleteBookGetMethod() throws Exception {
        mvc.perform(get("/api/deleteBook/1"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void deleteBookPutMethod() throws Exception {
        mvc.perform(put("/api/deleteBook/1"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void deleteBookPostMethod() throws Exception {
        mvc.perform(post("/api/deleteBook/1"))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void deleteBookDeleteMethod() throws Exception {
        doNothing().when(bookService).deleteBook(1L);
        mvc.perform(delete("/api/deleteBook/1"))
                .andExpect(status().isOk())
        ;
    }

    @Test
    public void updateBookGetMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPut);
        mvc.perform(get("/api/updateBook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void updateBookPutMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPut);
        when(bookService.updateBook(bookToPut,0L)).thenReturn(bookToPut);
        mvc.perform(put("/api/updateBook/0")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    public void updateBookPostMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPut);
        mvc.perform(post("/api/updateBook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void updateBookDeleteMethod() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String jsonContent = mapper.writeValueAsString(bookToPut);
        mvc.perform(delete("/api/updateBook/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andExpect(status().isMethodNotAllowed());
    }

}
