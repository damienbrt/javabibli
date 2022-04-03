package com.limayrac.bibli.controller;

import com.limayrac.bibli.model.Livre;
import com.limayrac.bibli.model.User;
import com.limayrac.bibli.service.LivreService;
import com.limayrac.bibli.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/livre")
public class LivreController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private LivreService livreService;

    @GetMapping("/lister")
    public ModelAndView listeLivre(){
        return new ModelAndView("livre/listelivre", "livre", livreService.getLivres());
    }

    @GetMapping("/lister/{id}")
    public ModelAndView detailLivre(@PathVariable("id") final Integer id){
        Optional<Livre> livre = livreService.getLivre(id);
        return new ModelAndView("livre/detaillivre", "livre", livre.orElse(null));
    }

    @GetMapping("/creer")
    public String creerLivre(Model model) {
        Livre livre = new Livre();
        model.addAttribute("livre", livre);
        Iterable<User> users = userService.getUsers();
        model.addAttribute("users",users);
        return "livre/creerlivre";
    }

    @PostMapping("/creer")
    public ModelAndView submitCreer(@ModelAttribute("livre") Livre livre) {
        livreService.saveLivre(livre);
        return listeLivre();
    }

    @RequestMapping("/editer")
    public String editerLivre(){
        return "livre/editerlivre";
    }

    @RequestMapping("/effacer/{id}")
    public ModelAndView effacerLivre(@PathVariable int id) {
        livreService.deleteLivre(id);
        return listeLivre();
    }
}
