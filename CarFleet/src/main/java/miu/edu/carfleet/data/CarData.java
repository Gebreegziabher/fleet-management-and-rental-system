package miu.edu.carfleet.data;

import miu.edu.carfleet.common.Availability;
import miu.edu.carfleet.dto.CarDto;

import java.util.Arrays;
import java.util.List;

public class CarData {

    static CarDto car1 = new CarBuilder().licensePlate("IA-450-210").type("Mustang").brand("Ford").price(30000).availability(Availability.AVAILABLE).build();
    static CarDto car2 = new CarBuilder().licensePlate("IA-450-211").type("Mustang").brand("Ford").price(40000).availability(Availability.AVAILABLE).build();
    static CarDto car3 = new CarBuilder().licensePlate("IA-450-212").type("Mustang").brand("Ford").price(50000).availability(Availability.AVAILABLE).build();
    static CarDto car4 = new CarBuilder().licensePlate("IA-450-213").type("Accord").brand("Honda").price(35000).availability(Availability.AVAILABLE).build();
    static CarDto car5 = new CarBuilder().licensePlate("IA-450-214").type("Accord").brand("Honda").price(38000).availability(Availability.AVAILABLE).build();
    static CarDto car6 = new CarBuilder().licensePlate("IA-450-215").type("Accord").brand("Honda").price(48000).availability(Availability.AVAILABLE).build();

    private static final List<CarDto> dtos = Arrays.asList(car1, car2, car3, car4, car5, car6);

    public static List<CarDto> getDtoData() {
        return dtos;
    }
}
