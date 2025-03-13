package io.github.nvbao.springdemo;

import io.github.nvbao.springdemo.springioc.excercise_1.service.VehicleService;
import io.github.nvbao.springdemo.springioc.excercise_2.service.UserServiceExcercise2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringDemoApplication.class, args);

        VehicleService vehicleService = context.getBean(VehicleService.class);
        vehicleService.startBike();
        vehicleService.stopBike();
        vehicleService.startCar();
        vehicleService.stopCar();

        UserServiceExcercise2 userServiceExcercise2 = context.getBean(UserServiceExcercise2.class);
        userServiceExcercise2.getUserById(1);
        userServiceExcercise2.updateUser(2, "Alice");
    }

}
