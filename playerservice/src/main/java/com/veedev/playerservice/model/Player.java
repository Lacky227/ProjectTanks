package com.veedev.playerservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "players")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private int level;

    @Column(nullable = false)
    private int experience;

    private String avatar;
}
