package com.github.petkovicdanilo.freelance.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Technology {
    @Id
    @GeneratedValue
    private int id;

    private String name;
}
