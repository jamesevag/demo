package de.adesso.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users") // Define table name
@NoArgsConstructor
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
