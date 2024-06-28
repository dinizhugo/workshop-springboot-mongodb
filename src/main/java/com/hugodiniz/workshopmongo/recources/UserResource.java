package com.hugodiniz.workshopmongo.recources;

import com.hugodiniz.workshopmongo.domain.User;
import com.hugodiniz.workshopmongo.dto.RequestUserDTO;
import com.hugodiniz.workshopmongo.dto.UserDTO;
import com.hugodiniz.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> dtoList = list.stream().map(x -> new UserDTO(x.getId(), x.getName(), x.getEmail())).toList();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findUserById(@PathVariable String id) {
        User obj = service.findById(id);

        return ResponseEntity.ok().body(new UserDTO(obj.getId(), obj.getName(), obj.getEmail()));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody RequestUserDTO data) {
        User user = new User();
        user.setName(data.name());
        user.setEmail(data.email());
        service.insert(user);
        return ResponseEntity.
                created(
                    ServletUriComponentsBuilder.
                            fromCurrentRequest().
                            path("/{id}").
                            buildAndExpand(user.getId()).
                            toUri()
                )
                .build();
    }
}
