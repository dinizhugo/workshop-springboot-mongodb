package com.hugodiniz.workshopmongo.config;

import com.hugodiniz.workshopmongo.domain.Post;
import com.hugodiniz.workshopmongo.domain.User;
import com.hugodiniz.workshopmongo.dto.AuthorDTO;
import com.hugodiniz.workshopmongo.dto.CommentDTO;
import com.hugodiniz.workshopmongo.repositories.PostRepository;
import com.hugodiniz.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
public class Instantiation implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(Arrays.asList(maria,alex,bob));

        Post post1 = new Post(null, simpleDateFormat.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo, Abraços", new AuthorDTO(maria.getId(), maria.getName()));
        Post post2 = new Post(null, simpleDateFormat.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje", new AuthorDTO(maria.getId(), maria.getName()));

        CommentDTO c1 = new CommentDTO("Boa viagem mano!", simpleDateFormat.parse("21/03/2018"), new AuthorDTO(alex.getId(), alex.getName()));
        CommentDTO c2 = new CommentDTO("Aproveite!", simpleDateFormat.parse("22/03/2018"), new AuthorDTO(bob.getId(), bob.getName()));
        CommentDTO c3 = new CommentDTO("Tenha um otimo dia!", simpleDateFormat.parse("23/03/2018"), new AuthorDTO(alex.getId(), alex.getName()));

        post1.getComments().addAll(Arrays.asList(c1,c2));
        post2.getComments().addAll(Arrays.asList(c3));

        postRepository.saveAll(Arrays.asList(post1, post2));

        maria.getPosts().addAll(Arrays.asList(post1, post2));
        userRepository.save(maria);

    }
}
