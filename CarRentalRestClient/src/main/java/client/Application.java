package client;

import client.config.CarRentalConfig;
import client.data.CustomersData;
import client.dto.*;
import client.gateway.CarsGateway;
import client.gateway.CustomersGateway;
import client.gateway.ReservationsGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({CarRentalConfig.class})
public class Application implements CommandLineRunner {

    @Autowired
    private CustomersGateway customerGateway;

    @Autowired
    private CarsGateway carsGateway;

    @Autowired
    private ReservationsGateway reservationsGateway;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        //Insert CUSTOMERS
        for (CustomerDto customer : CustomersData.getCustomers())
            customerGateway.createCustomer(customer);

        //Fetch all CUSTOMERS
        System.out.printf("\n%-15s %-20s %-30s%n", "Customer Number", "Name", "Email");
        System.out.println("------------------------------------------------");
        for (CustomerDto customer : customerGateway.getCustomers().getCustomersDto()) {
            System.out.printf("%-15d %-20s %-30s%n", customer.getCustomerNumber(), customer.getName(), customer.getEmail());
        }

        System.out.println("\nALL CARS INITIALLY");
        printCars(carsGateway.getCars().getCars());

        //Make RESERVATION
        long customerNumber = 10001L;
        String licensePlate = "IA-450-210";
        ReservationRequestDto reservationRequestDto = new ReservationRequestDto(new Date(), new Date(), customerNumber, licensePlate);
        reservationsGateway.reserve(reservationRequestDto);

        //Fetch RESERVATION
        System.out.printf("\n%-10s %-12s %-12s %-20s %-20s%n", "ID", "Starting On", "Ending On", "Customer", "Car");
        System.out.println("------------------------------------------------------------");

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        for (ReservationDto reservation : reservationsGateway.getReservations().getReservations()) {
            System.out.printf("%-10d %-12s %-12s %-20s %-20s%n", reservation.getId(), dateFormat.format(reservation.getStartingOn()), dateFormat.format(reservation.getEndingOn()), reservation.getCustomer().getName(), reservation.getCar().getLicensePlate());
        }

        System.out.println("\nALL CARS AFTER RESERVING ONE CAR");
        printCars(carsGateway.getCars().getCars());

        //Rent out a car
        long reservationId = 1L;
        RentalDto rentalDto = new RentalDto();
        rentalDto.setRentalLocation("Fairfield");
        rentalDto.setRentOutProcessedBy("Bob Smith");
        rentalDto.setRentedOutDate(new Date());
        reservationsGateway.rentOut(reservationId, rentalDto);

        System.out.println("\nALL CARS AFTER RENTING OUT ONE CAR");
        printCars(carsGateway.getCars().getCars());

        //Return a car
        reservationId = 1L;
        rentalDto = new RentalDto();
        rentalDto.setReturnProcessedBy("Jack Harvey");
        rentalDto.setReturnedDate(new Date());
        reservationsGateway.returnAndPay(reservationId, rentalDto);

        System.out.println("\nALL CARS AFTER RETURNING THE CAR");
        printCars(carsGateway.getCars().getCars());
    }

    private static void printCars(List<CarDto> cars){
        System.out.printf("\n%-15s %-10s %-10s %-10s %-15s%n", "License Plate", "Type", "Brand", "Price", "Availability");
        System.out.println("--------------------------------------------------------------");

        for (CarDto car : cars) {
            System.out.printf("%-15s %-10s %-10s %-10.2f %-15s%n", car.getLicensePlate(), car.getType(), car.getBrand(), car.getPrice(), car.getAvailability());
        }
    }
}
