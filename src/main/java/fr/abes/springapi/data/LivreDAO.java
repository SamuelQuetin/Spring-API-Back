package fr.abes.springapi.data;

import fr.abes.springapi.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface LivreDAO extends JpaRepository<Livre,Long> {
}
