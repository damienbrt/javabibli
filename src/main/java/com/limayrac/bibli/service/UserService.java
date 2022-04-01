package com.limayrac.bibli.service;

import com.limayrac.bibli.model.User;
import com.limayrac.bibli.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(final Integer id) {
        return userRepository.findById(id);
    }

    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(final Integer id){
        userRepository.deleteById(id);
    }

    public User saveUser(User client) {
        return userRepository.save(client);
    }
}
