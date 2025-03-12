package io.github.nvbao.springdemo.springioc.excercise_3.service;


import io.github.nvbao.springdemo.springioc.excercise_3.entity.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong(1); //Create automatic ID

    public List<User> getAllUsers() {
        return users;
    }

    public Optional<User> getUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }

    public User createUser(User user) {
        user.setId(counter.getAndIncrement()); // Create automatic ID
        users.add(user);
        return user;
    }

    public User updateUser(Long id, User userDetails) {
        return getUserById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return user;
        }).orElse(null);
    }

    public boolean deleteUser(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }
}
