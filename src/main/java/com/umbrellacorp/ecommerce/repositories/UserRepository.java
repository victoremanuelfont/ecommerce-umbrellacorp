package com.umbrellacorp.ecommerce.repositories;

import com.umbrellacorp.ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
