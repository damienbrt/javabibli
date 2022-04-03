package com.limayrac.bibli.service;

import com.limayrac.bibli.model.Livre;
import com.limayrac.bibli.repository.LivreRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;

    public Optional<Livre> getLivre(final Integer id) {
        return livreRepository.findById(id);
    }

    public Iterable<Livre> getLivres(){
        return livreRepository.findAll();
    }

    public void deleteLivre(final Integer id){
        livreRepository.deleteById(id);
    }

    public Livre saveLivre(Livre livre) {
        return livreRepository.save(livre);
    }
}
