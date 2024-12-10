package com.vatsaladhiya.project.uber.uberApp.entities;

import jakarta.persistence.*;

@Entity
public class Rider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private Double rating;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
