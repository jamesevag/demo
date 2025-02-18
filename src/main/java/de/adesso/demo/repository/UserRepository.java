package de.adesso.demo.repository;

import de.adesso.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Custom query method to find a user by email (Optional to handle null safely)
    Optional<User> findByEmail(String email);
    List<User> findByLastName(String lastName);
}