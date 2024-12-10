package com.vatsaladhiya.project.uber.uberApp.entities;

import jakarta.persistence.*;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private Double rating;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Boolean available;

}
