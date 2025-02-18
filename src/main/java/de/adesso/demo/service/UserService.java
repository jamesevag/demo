package de.adesso.demo.service;

import de.adesso.demo.entity.User;
import de.adesso.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    /**
     * Get a user by their email address.
     *
     * @param email the email address of the user
     * @return an Optional containing the user if found, empty otherwise
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Create a new user or update an existing user.
     *
     * @param user the user to be created or updated
     * @return the saved user entity
     */
    public User saveOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Get all users.
     *
     * @return a list of all users
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Delete a user by their ID.
     *
     * @param id the ID of the user to be deleted
     */
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Check if a user exists by email.
     *
     * @param email the email of the user
     * @return true if user exists, false otherwise
     */
    public boolean userExistsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    /**
     * Delete a user by email.
     *
     * @param email the email of the user to be deleted
     */
    public void deleteUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        user.ifPresent(value -> userRepository.deleteById(value.getId()));
    }
}