package miu.edu.carfleet.dto;

import java.util.List;

public class CarsAvailabilityDto {
    private List<CarAvailabilityDto> carsAvailability;

    public CarsAvailabilityDto() {
    }

    public CarsAvailabilityDto(List<CarAvailabilityDto> carsAvailability) {
        this.carsAvailability = carsAvailability;
    }

    public List<CarAvailabilityDto> getCarsAvailability() {
        return carsAvailability;
    }

    public void setCarAvailability(List<CarAvailabilityDto> carsAvailability) {
        this.carsAvailability = carsAvailability;
    }
}
