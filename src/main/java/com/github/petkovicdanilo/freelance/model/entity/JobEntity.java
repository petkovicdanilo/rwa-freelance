package com.github.petkovicdanilo.freelance.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Job")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 64, nullable = false)
    private String title;

    @Column(length = 1024, nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;
}
