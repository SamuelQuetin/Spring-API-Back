package fr.abes.springapi.controller;

import fr.abes.springapi.data.LivreDAO;
import fr.abes.springapi.model.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LivreController {

    @Autowired
    private LivreDAO livreDAO;


    @GetMapping("/getLivre")
    public String showLivre(Model model){
        model.addAttribute("livres",livreDAO.findAll());
        model.addAttribute("livre", new Livre());

        return "LivrePage";
    }

    @PostMapping("/postLivre")
    public String postLivre(@ModelAttribute Livre newLivre){
        Livre livre = new Livre(newLivre.getAuthor(), newLivre.getTitle(), newLivre.getDescription());

        livreDAO.save(livre);
        return "redirect:getLivre";
    }
}
//    Livre livre = new Livre("Emile Zola","L'etrangé","l'histoir d'un etranger bref je sais plus mais a la fin il va en prison");
//    Livre livre2 = new Livre("Un Mec","Gargantua","C'est un petit garcon qui est super grand et qui mange bcp");
//
//        livres.add(livre);
//                livres.add(livre2);