package client;

import client.config.CarFleetConfig;
import client.data.CarData;
import client.dto.CarAvailabilityDto;
import client.dto.CarDto;
import client.dto.CarsDto;
import client.gateway.CarsAvailabilityGateway;
import client.gateway.CarsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({CarFleetConfig.class})
public class Application implements CommandLineRunner {

    @Autowired
    private CarsGateway carsGateway;

    @Autowired
    CarsAvailabilityGateway carsAvailabilityGateway;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (CarDto carDto : CarData.getDtoData())
            carsGateway.createCar(carDto);

        System.out.println("\nALL CARS ");
        System.out.printf("\n%-15s %-10s %-10s %-10s %-15s%n", "License Plate", "Type", "Brand", "Price", "Availability");
        System.out.println("--------------------------------------------------------------");

        for (CarDto car : carsGateway.getCars().getCars()) {
            System.out.printf("%-15s %-10s %-10s %-10.2f %-15s%n", car.getLicensePlate(), car.getType(), car.getBrand(), car.getPrice(), car.getAvailability());
        }

        System.out.println("\nAVAILABILITY OF CARS ");
        System.out.printf("%-10s %-10s %-10s%n", "Type", "Brand", "Quantity");
        System.out.println("----------------------------------------------");

        for (CarAvailabilityDto car : carsAvailabilityGateway.getCars().getCarsAvailability()) {
            System.out.printf("%-10s %-10s %-10d%n", car.getType(), car.getBrand(), car.getQuantity());
        }
    }
}
