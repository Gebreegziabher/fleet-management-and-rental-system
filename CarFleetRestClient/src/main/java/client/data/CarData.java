package client.data;

import client.common.Availability;
import client.dto.CarDto;

import java.util.Arrays;
import java.util.List;

public class CarData {
    private static List<CarDto> dtos = Arrays.asList(
            new CarDto("IA-450-210", "Mustang", "Ford", 30000, Availability.AVAILABLE),
            new CarDto("IX-450-233", "Mustang", "Ford", 40000, Availability.AVAILABLE),
            new CarDto("IY-450-234", "Mustang", "Ford", 50000, Availability.AVAILABLE),
            new CarDto("IC-450-212", "Accord", "Honda", 25000, Availability.AVAILABLE),
            new CarDto("ID-450-213", "Accord", "Honda", 30000, Availability.AVAILABLE),
            new CarDto("JA-450-236", "Accord", "Honda", 28000, Availability.AVAILABLE),
            new CarDto("II-450-218", "Corolla", "Toyota", 38000, Availability.AVAILABLE),
            new CarDto("IJ-450-219", "Corolla", "Toyota", 42000, Availability.AVAILABLE),
            new CarDto("IK-450-220", "Corolla", "Toyota", 28000, Availability.AVAILABLE)
    );

    public static List<CarDto> getDtoData() {
        return dtos;
    }
}
