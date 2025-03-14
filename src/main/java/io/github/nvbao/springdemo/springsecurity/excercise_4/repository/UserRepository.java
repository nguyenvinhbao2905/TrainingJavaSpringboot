package io.github.nvbao.springdemo.springsecurity.excercise_4.repository;

import io.github.nvbao.springdemo.springsecurity.excercise_4.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
