package com.hugodiniz.workshopmongo.services;

import com.hugodiniz.workshopmongo.domain.Post;
import com.hugodiniz.workshopmongo.repositories.PostRepository;
import com.hugodiniz.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PostService {

    @Autowired
    private PostRepository repository;

    public Post findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post não encontrado."));
    }

    public List<Post> findByTitle(String text) {
        return repository.searchTitle(text);
    }

}
