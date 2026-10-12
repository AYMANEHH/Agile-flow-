package com.example.gestion.de.projets.agiles.Entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 150)
    private String name;

    @Column(length = 1000)
    private String description;

    private LocalDate startDate;

    private LocalDate endDate;


    @Column(nullable = false)
    private String status = "PLANNING";
}
