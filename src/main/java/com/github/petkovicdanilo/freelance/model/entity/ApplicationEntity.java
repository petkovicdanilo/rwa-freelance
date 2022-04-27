package com.github.petkovicdanilo.freelance.model.entity;

import com.github.petkovicdanilo.freelance.model.common.ApplicationStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "Application")
public class ApplicationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 1024, nullable = false)
    private String text;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    ApplicationStatus status = ApplicationStatus.PENDING;

    @ManyToOne
    @NaturalId
    private UserEntity candidate;

    @ManyToOne
    @NaturalId
    private JobEntity job;

}