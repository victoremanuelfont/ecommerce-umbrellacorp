package com.umbrellacorp.ecommerce.config;


import java.time.LocalDate;
import com.umbrellacorp.ecommerce.entities.Order;
import com.umbrellacorp.ecommerce.entities.OrderStatus;
import com.umbrellacorp.ecommerce.entities.User;
import com.umbrellacorp.ecommerce.repositories.OrderRepository;
import com.umbrellacorp.ecommerce.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {

        // 1. Limpar o banco (opcional, mas bom para testes repetitivos)
        // Se usar H2 em memória não precisa, mas não custa nada.

        // 2. Criar Usuários (Dados Dummy)
        // (null no ID para o banco gerar automático)
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", LocalDate.parse("1990-06-20"));
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", LocalDate.parse("1985-03-15"));
        // 3. Salvar Usuários no Banco
        userRepository.saveAll(Arrays.asList(u1, u2));

        // 4. Criar Pedidos (Associando aos usuários criados acima)
        Order o1 = new Order(null, Instant.parse("2025-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2025-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2025-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

        // 5. Salvar Pedidos
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        System.out.println("---- DATABASE SEEDING CONCLUÍDO COM SUCESSO ----");
    }}