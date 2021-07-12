package fr.abes.springapi.controller;

import fr.abes.springapi.data.LivreDAO;
import fr.abes.springapi.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class LivreRestController {

    @Autowired
    private LivreDAO livreDAO;

    @GetMapping("/api/getLivres")
    public List<Livre> livreList(){
        return livreDAO.findAll();
    }

    @PostMapping("/api/postLivre")
    public void saveLivre(@RequestBody Livre livre){
        livreDAO.save(livre);
    }
}
