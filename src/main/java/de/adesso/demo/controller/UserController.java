package de.adesso.demo.controller;


import de.adesso.demo.entity.User;
import de.adesso.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        User user=new User();
        user.setEmail("test@gmail.com");
        user.setFirstName("Dimi2");
        user.setLastName("Dimi2");
        user.setId(1l);
        allUsers.add(user);
        return new ResponseEntity<>(allUsers,HttpStatus.OK);  // The view resolver maps this to "welcome.html" or "welcome.jsp"
    }

    @Operation(summary = "Creates a new user", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200")
    })
    @PostMapping("/user/creation")
    public ResponseEntity<Void> createUser(String firstName, String lastName,String email) {
        return new ResponseEntity<>(HttpStatus.CREATED);  // The view resolver maps this to "welcome.html" or "welcome.jsp"
    }

    @Operation(summary = "Deletes a user", responses = {
            @ApiResponse(description = "Successful Operation", responseCode = "200")
    })
    @PostMapping("/user/deletion")
    public ResponseEntity<Void> deleteUser(String firstName, String lastName,String email) {
        return new ResponseEntity<>(HttpStatus.CREATED);  // The view resolver maps this to "welcome.html" or "welcome.jsp"
    }
}
