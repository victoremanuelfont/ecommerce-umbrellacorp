package com.umbrellacorp.ecommerce.resources;

import com.umbrellacorp.ecommerce.entities.User;
import com.umbrellacorp.ecommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Fala pro Spring: "Isso aqui é um controlador Web"
@RequestMapping(value = "/users") // Define o endereço do site
public class UserResource {

    @Autowired
    private UserService service;

    // Endpoint para buscar TODOS
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // Endpoint para buscar por ID
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }
}