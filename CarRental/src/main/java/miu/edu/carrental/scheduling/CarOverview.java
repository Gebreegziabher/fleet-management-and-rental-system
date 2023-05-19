package miu.edu.carrental.scheduling;

import miu.edu.carrental.dto.CarDto;
import miu.edu.carrental.dto.CarsDto;
import miu.edu.carrental.gateway.CarFleetGateway;
import miu.edu.carrental.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CarOverview {
    @Autowired
    private CarFleetGateway carFleetGateway;

    private final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Scheduled(fixedRate = 20000)
    public void printOverview() {
        CarsDto carsDto = carFleetGateway.findAll();

        logger.warn("Car Fleet Management System accessed!");

        System.out.printf("\n%-15s %-10s %-10s %-10s %-15s%n", "License Plate", "Type", "Brand", "Price", "Availability");
        System.out.println("------------------------------------------------------------");

        for (CarDto car : carsDto.getCars()) {
            System.out.printf("%-15s %-10s %-10s %-10.2f %-15s%n", car.getLicensePlate(), car.getType(), car.getBrand(), car.getPrice(), car.getAvailability());
        }
    }
}
