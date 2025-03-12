package io.github.nvbao.springdemo.springioc.excercise_1.service;

import io.github.nvbao.springiocexample.excercise_1.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Qualifier("bike")
    @Autowired
    private Vehicle bike;

    @Qualifier("car")
    @Autowired
    private Vehicle car;

    public void startBike() {
        bike.start();
    }
    public void stopBike() {
        bike.stop();
    }

    public void startCar() {
        car.start();
    }
    public void stopCar() {
        car.stop();
    }



}
