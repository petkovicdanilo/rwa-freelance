package com.github.petkovicdanilo.freelance.model;

import lombok.*;

@AllArgsConstructor
@Data
@ToString
public class Job {
    private int id;
    private String description;
    private double price;
}
