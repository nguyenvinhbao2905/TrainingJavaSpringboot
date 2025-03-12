package io.github.nvbao.springdemo.springioc.excercise_2.service;

import org.springframework.stereotype.Service;

@Service
public class UserServiceExcercise2 {
    public String getUserById(int id) {
        System.out.println("Executing getUserById...");
        return "User_" + id;
    }

    public void updateUser(int id, String name) {
        System.out.println("Executing updateUser...");
    }
}
