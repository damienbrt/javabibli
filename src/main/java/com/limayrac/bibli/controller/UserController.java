package com.limayrac.bibli.controller;

import fr.limayrac.model.Client;
import fr.limayrac.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/lister")
    public ModelAndView listeLivre() {
        return new ModelAndView("user/livre", "user", clientService.getClients());
    }

    @GetMapping("/lister/{id}")
    public ModelAndView detailClient(@PathVariable("id") final Integer id) {
        Optional<Client> client = clientService.getClient(id);
        return new ModelAndView("client/detailclient", "client", client.orElse(null));
    }

    @GetMapping("/creer")
    public ModelAndView creerClient() {
        Client client = new Client();
        return new ModelAndView("client/creerclient", "client", client);
    }

    @PostMapping("/creer")
    public ModelAndView submitCreer(@ModelAttribute("client") Client client, ModelMap model) {
        model.addAttribute("nom", client.getNom());
        model.addAttribute("prenom", client.getPrenom());
        clientService.saveClient(client);
        return listeClient();
    }

    @RequestMapping("/editer")
    public String editerClient() {
        return "client/editerclient";
    }

    @RequestMapping("/effacer/{id}")
    public ModelAndView effacerClient(@PathVariable int id) {
        clientService.deleteClient(id);
        return listeClient();
    }
}
