package fr.abes.springapi.data;

import fr.abes.springapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IBookDAO extends JpaRepository<Book,Long> {
    @Query("SELECT b FROM Book b WHERE b.title LIKE %?1%"
            + " OR b.author LIKE %?1%"
            + " OR b.description LIKE %?1%")
    public List<Book> search(String keyword);
}
