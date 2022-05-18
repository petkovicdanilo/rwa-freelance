package com.github.petkovicdanilo.freelance.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @Builder.Default
    private boolean active = true;

    @ManyToOne(optional = false)
    @JoinColumn
    private UserEntity employer;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        joinColumns = @JoinColumn(name = "job_id"),
        inverseJoinColumns = @JoinColumn(name = "technology_id")
    )
    @Singular
    private Set<TechnologyEntity> technologies;

    @OneToMany(mappedBy = "job")
    @Singular
    private List<ApplicationEntity> applications;
}
