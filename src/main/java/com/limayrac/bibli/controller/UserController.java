package com.limayrac.bibli.controller;

import com.limayrac.bibli.model.User;
import com.limayrac.bibli.service.LivreService;
import com.limayrac.bibli.service.UserService;
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

    @Autowired
    private UserService userService;

    @GetMapping("/lister")
    public ModelAndView listeUser() {
        return new ModelAndView("user/listeuser", "users", userService.getUsers());
    }

    @GetMapping("/lister/{id}")
    public ModelAndView detailUser(@PathVariable("id") final Integer id) {
        Optional<User> user = userService.getUser(id);
        return new ModelAndView("user/detailuser", "users", user.orElse(null));
    }

    @GetMapping("/creer")
    public ModelAndView creerUser() {
        User user = new User();
        return new ModelAndView("user/creeruser", "users", user);
    }

    @PostMapping("/creer")
    public ModelAndView submitCreer(@ModelAttribute("users") User user, ModelMap model) {
        userService.saveUser(user);
        return listeUser();
    }

    @RequestMapping("/editer")
    public String editerUser() {
        return "user/editeruser";
    }

    @RequestMapping("/effacer/{id}")
    public ModelAndView effacerUser(@PathVariable int id) {
        userService.deleteUser(id);
        return listeUser();
    }


}
