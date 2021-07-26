package fr.abes.springapi;

import fr.abes.springapi.model.Book;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookTest {

    private Book book;


    private Book bookGet;


    @BeforeEach
    void setUp() {
        bookGet = new Book("camus", "etrange", "description");
    }

    @Test
    void testConstructorBookEmpty(){
        book = new Book();
        assertNotNull(book);
    }

    @Test
    void testConstructorBook(){
        book = new Book("camus","etrange","description");
        assertNotNull(book);
    }

    // ----------------  test getter -------------------
    @Test
    void testGetAuthor(){
        String author = bookGet.getAuthor();
        assertEquals("camus",author);
    }

    @Test
    void testGetTitle(){
        String title = bookGet.getTitle();
        assertEquals("etrange",title);
    }

    @Test
    void testGetDescription(){
        String description = bookGet.getDescription();
        assertEquals("description",description);
    }

    @Test
    void testGetId(){ // un truc que je n'ai pas compris dans spring
        Long id = bookGet.getId();
        assertNull(id);
    }

    // -------------- Setter ----------------
    @Test
    void testSetAuthor() {
        bookGet.setAuthor("toto");
        String author = bookGet.getAuthor();
        assertEquals("toto",author);
    }

    @Test
    void testSetTitle() {
        bookGet.setTitle("toto en vacances");
        String title = bookGet.getTitle();
        assertEquals("toto en vacances",title);
    }

    @Test
    void testSetDescription() {
        bookGet.setDescription("la description");
        String description = bookGet.getDescription();
        assertEquals("la description",description);
    }

    @Test
    void testSetId() {
        bookGet.setId(5L);
        Long id = bookGet.getId();
        assertEquals(5L,id);
    }
}
