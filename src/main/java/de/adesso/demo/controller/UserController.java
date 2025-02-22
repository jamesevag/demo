package de.adesso.demo.controller;


import de.adesso.demo.dto.UserDTO;
import de.adesso.demo.entity.User;
import de.adesso.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @Operation(summary = "Get all users", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200")
    })
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> allUsers = userService.getAllUsers();
        addMockedUser(allUsers);
        return new ResponseEntity<>(allUsers,HttpStatus.OK);  // The view resolver maps this to "welcome.html" or "welcome.jsp"
    }

    @Operation(summary = "Creates a new user", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200")
    })
    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody User user) {
        userService.saveOrUpdateUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);  // The view resolver maps this to "welcome.html" or "welcome.jsp"
    }

    @Operation(summary = "Deletes a user", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200")
    })
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {  // Changed @PathParam to @PathVariable
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    private static void addMockedUser(List<UserDTO> allUsers) {
        UserDTO user=new UserDTO(99l,"Dimi","Evangelopoulos","test@gmail.com");
        allUsers.add(user);
    }

}
