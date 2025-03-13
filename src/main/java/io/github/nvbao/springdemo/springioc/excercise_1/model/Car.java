package io.github.nvbao.springdemo.springioc.excercise_1.model;


import io.github.nvbao.springdemo.springioc.excercise_1.Vehicle;
import org.springframework.stereotype.Component;


@Component
public class Car implements Vehicle {
    @Override
    public void start() {
        System.out.println("Car start");
    }

    @Override
    public void stop() {
        System.out.println("Car stop");
    }
}
