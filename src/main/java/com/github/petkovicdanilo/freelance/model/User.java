package com.github.petkovicdanilo.freelance.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @ColumnDefault("UNKNOWN")
    @Builder.Default
    private Gender gender = Gender.UNKNOWN;

    public enum Gender{
        FEMALE,
        MALE,
        UNKNOWN,
    };
}
