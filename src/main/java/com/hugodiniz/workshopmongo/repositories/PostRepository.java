package com.hugodiniz.workshopmongo.repositories;

import com.hugodiniz.workshopmongo.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    @Query("{ 'title': {$regex: ?0, $options: 'i'} }") //"?0" indica que vai receber o primeiro parametro do método
    List<Post> searchTitle(String txt);

    List<Post> findByTitleContainingIgnoreCase(String text);
}
