package de.adesso.demo.service;

import de.adesso.demo.dto.UserDTO;
import de.adesso.demo.entity.User;
import de.adesso.demo.mapper.UserMapper;
import de.adesso.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Get a user by their email address.
     *
     * @param email the email address of the user
     * @return an Optional containing the user if found, empty otherwise
     */
    public UserDTO getUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(userMapper::userToUserDTO).orElse(null);
    }

    /**
     * Create a new user or update an existing user.
     *
     * @param user the user to be created or updated
     * @return the saved user entity
     */
    public UserDTO saveOrUpdateUser(User user) {
        User userSaved=userRepository.save(user);
        return userMapper.userToUserDTO(userSaved);
    }

    /**
     * Get all users.
     *
     * @return a list of all users
     */
    public List<UserDTO> getAllUsers() {
        return userMapper.usersToUserDTOList(userRepository.findAll());
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