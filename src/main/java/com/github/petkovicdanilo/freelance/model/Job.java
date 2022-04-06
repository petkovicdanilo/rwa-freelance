package com.github.petkovicdanilo.freelance.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 1024, nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;
}
