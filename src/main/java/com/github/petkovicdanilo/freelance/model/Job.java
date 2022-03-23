package com.github.petkovicdanilo.freelance.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@Data
public class Job {
    @Id
    @GeneratedValue
    private int id;

    @Column(length = 1024)
    private String description;

    private double price;
}
