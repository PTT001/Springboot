package com.ray.raydemo.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game_records")
@Data
public class GameRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "blue_records", joinColumns = @JoinColumn(name = "game_record_id"))
    @Column(name = "username")
    private List<String> blue = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "green_records", joinColumns = @JoinColumn(name = "game_record_id"))
    @Column(name = "username")
    private List<String> green = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "red_records", joinColumns = @JoinColumn(name = "game_record_id"))
    @Column(name = "username")
    private List<String> red = new ArrayList<>();

    @Column(name = "winner")
    private String winner;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}