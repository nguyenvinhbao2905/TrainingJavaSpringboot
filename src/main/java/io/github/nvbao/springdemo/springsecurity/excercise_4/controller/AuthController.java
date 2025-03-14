package io.github.nvbao.springdemo.springsecurity.excercise_4.controller;


import io.github.nvbao.springdemo.springsecurity.excercise_4.config.JwtUtil;
import io.github.nvbao.springdemo.springsecurity.excercise_4.entity.User;
import io.github.nvbao.springdemo.springsecurity.excercise_4.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) throws Exception {
        return ResponseEntity.ok(JwtUtil.generateToken(request.get("username"), "ROLE_USER"));
    }
}
