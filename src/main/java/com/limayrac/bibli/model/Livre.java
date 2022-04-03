package com.limayrac.bibli.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "livre")
@Data
public class Livre {

    @Id
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

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    public Livre() {
    }
}
