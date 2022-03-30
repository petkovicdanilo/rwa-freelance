package com.github.petkovicdanilo.freelance.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    @Id
    @GeneratedValue
    private int id;

    @Column(length = 1024, nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;
}
