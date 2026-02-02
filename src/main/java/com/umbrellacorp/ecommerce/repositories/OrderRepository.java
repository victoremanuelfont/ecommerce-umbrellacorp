package com.umbrellacorp.ecommerce.repositories;

import com.umbrellacorp.ecommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
