package fr.abes.springapi.data;

import fr.abes.springapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface BookDAO extends JpaRepository<Book,Long> {
}
