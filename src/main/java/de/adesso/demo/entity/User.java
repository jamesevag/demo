package de.adesso.demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users") // Define table name
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String firstName;
        private String lastName;
        private String email;

    }
