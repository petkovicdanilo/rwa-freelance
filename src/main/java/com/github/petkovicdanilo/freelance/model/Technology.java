package com.github.petkovicdanilo.freelance.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class Technology {
    @Id
    @GeneratedValue
    private int id;

    private String name;
}
