package com.github.petkovicdanilo.freelance.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @Column(nullable = false, length = 32)
    private String name;

    @ManyToMany(mappedBy = "technologies")
    private List<UserEntity> users;

    @ManyToMany(mappedBy = "technologies")
    private List<JobEntity> jobs;
}
