package com.umbrellacorp.ecommerce.services;

import com.umbrellacorp.ecommerce.entities.User;
import com.umbrellacorp.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // 1. Isso registra a classe no Spring como um componente de serviço
public class UserService {

    @Autowired
    private UserRepository repository;

    // Método para buscar TODOS os usuários
    public List<User> findAll() {
        return repository.findAll();
    }

    // Método para buscar UM usuário por ID
    public User findById(Long id) {
        Optional<User> obj = repository.findById(id);
        return obj.get(); // Retorna o usuário que está dentro do "Optional"
    }
}