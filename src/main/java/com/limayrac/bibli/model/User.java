package com.limayrac.bibli.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name= "user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "prenom", length = 50)
    private String prenom;

    @Column(name = "dateNaissance", length = 50)
    private String dateNaissance;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "motDePasse", length = 50)
    private String motDePasse;

    @Column(name = "roles", length = 50)
    private String roles;

    @OneToMany(mappedBy = "livre")
    private List<Livre> livre = new ArrayList<>();

    public User() {
    }

}
