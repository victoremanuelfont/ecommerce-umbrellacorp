package com.umbrellacorp.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant moment;

    private Integer status; // Gravamos como Inteiro no banco

    // Relacionamento: Vários pedidos para UM usuário
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    public Order() {
    }

    public Order(Long id, Instant moment, OrderStatus status, User client) {
        this.id = id;
        this.moment = moment;
        setStatus(status);
        this.client = client;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Instant getMoment() { return moment; }
    public void setMoment(Instant moment) { this.moment = moment; }

    // Truque para converter Integer <-> Enum
    public OrderStatus getStatus() {
        if (status == null) return null;
        return OrderStatus.values()[status]; // Cuidado: ordem importa aqui (Simplificado para Vibe Coding)
    }

    public void setStatus(OrderStatus status) {
        if (status != null) {
            this.status = status.ordinal();
        }
    }

    public User getClient() { return client; }
    public void setClient(User client) { this.client = client; }
}