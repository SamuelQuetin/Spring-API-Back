package fr.abes.springapi.data;

import fr.abes.springapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBookDAO extends JpaRepository<Book,Long> {
}
