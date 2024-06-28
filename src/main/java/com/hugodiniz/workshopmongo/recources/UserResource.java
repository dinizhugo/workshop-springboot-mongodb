package com.hugodiniz.workshopmongo.recources;

import com.hugodiniz.workshopmongo.domain.User;
import com.hugodiniz.workshopmongo.dto.UserDTO;
import com.hugodiniz.workshopmongo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
