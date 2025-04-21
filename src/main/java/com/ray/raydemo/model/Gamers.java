package com.ray.raydemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "gamers")
public class Gamers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String role;
}
