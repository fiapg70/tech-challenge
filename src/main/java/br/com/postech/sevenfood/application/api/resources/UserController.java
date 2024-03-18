package br.com.postech.sevenfood.application.api.resources;

import br.com.postech.sevenfood.application.api.dto.request.LoginRequest;
import br.com.postech.sevenfood.application.api.dto.request.SignupRequest;
import br.com.postech.sevenfood.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody SignupRequest request) {
        try {
            userService.createUser(request.getUsername(), request.getPassword());
            return ResponseEntity.ok("User registered successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error registering user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LoginRequest request) {
        String authenticate = userService.authenticate(request.getUsername(), request.getPassword());
        if (authenticate != null) { // Login user with Cognito
            return ResponseEntity.ok("User logged in successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid username or password.");
        }
    }
}