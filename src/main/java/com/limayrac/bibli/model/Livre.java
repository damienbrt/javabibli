package com.limayrac.bibli.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "livre")
@Data
public class Livre {

    @Id
    @Column(name = "livre_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nom", length = 50)
    private String nom;

    @Column(name = "nombre", length = 50)
    private Integer nombre;

    @Column(name = "dateEmprunt")
    private String dateEmprunt;

    @Column(name = "dateRetour")
    private String dateRetour;

    @Column(name = "delaiPret")
    private String delaiPret;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "livre_emprunt",
            joinColumns = @JoinColumn(name = "livre_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    public Livre() {
    }

}
