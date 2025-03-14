package io.github.nvbao.springdemo.springsecurity.excercise_3.controller;

import io.github.nvbao.springdemo.springsecurity.excercise_3.config.JwtUtil;
import io.github.nvbao.springdemo.springsecurity.excercise_3.entity.User;
import io.github.nvbao.springdemo.springsecurity.excercise_3.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Mã hóa mật khẩu
        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) throws Exception {
        return ResponseEntity.ok(JwtUtil.generateToken(request.get("username"), "USER"));
    }
}
