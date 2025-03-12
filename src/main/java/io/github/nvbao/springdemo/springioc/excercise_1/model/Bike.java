package io.github.nvbao.springdemo.springioc.excercise_1.model;

import io.github.nvbao.springiocexample.excercise_1.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class Bike implements Vehicle {
    @Override
    public void start() {
        System.out.println("Bike is running");
    }

    @Override
    public void stop() {
        System.out.println("Bike is stopped");
    }
}
