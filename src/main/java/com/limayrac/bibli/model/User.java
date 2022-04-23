package com.limayrac.bibli.model;

import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.*;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "password", length = 250)
    private String password;

    @Column(name = "dateNaissance", length = 50)
    private String dateNaissance;

    @Column(name = "email", length = 50)
    private String email;

    private boolean enabled;

    public void setPassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    public enum Provider {
        LOCAL, GOOGLE
    }

    @Enumerated(EnumType.STRING)
    private Provider provider;

    public Provider getProvider() {
        return provider;
    }

    public User() {
    }

    @ManyToMany(mappedBy = "users", cascade = { CascadeType.ALL })
    private Set<Livre> livres = new HashSet<Livre>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles = new HashSet<>();

}
