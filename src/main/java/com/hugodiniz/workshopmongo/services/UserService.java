package com.hugodiniz.workshopmongo.services;

import com.hugodiniz.workshopmongo.domain.User;
import com.hugodiniz.workshopmongo.repositories.UserRepository;
import com.hugodiniz.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado."));
    }
}
