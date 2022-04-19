package com.github.petkovicdanilo.freelance.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Technology")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnologyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;
}
