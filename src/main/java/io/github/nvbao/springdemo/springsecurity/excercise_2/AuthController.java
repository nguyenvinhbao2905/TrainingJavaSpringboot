package io.github.nvbao.springdemo.springsecurity.excercise_2;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtTokenUtil jwtTokenUtil;

    public AuthController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) throws Exception {
        String username = request.get("username");
        String password = request.get("password");

        if ("admin".equals(username) && "admin123".equals(password)) {
            return ResponseEntity.ok(Map.of("token", jwtTokenUtil.generateToken(username, "ADMIN")));
        }
        if ("user".equals(username) && "user123".equals(password)) {
            return ResponseEntity.ok(Map.of("token", jwtTokenUtil.generateToken(username, "USER")));
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
