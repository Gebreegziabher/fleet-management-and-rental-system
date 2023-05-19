package miu.edu.carfleet.adapter;

import miu.edu.carfleet.domain.CarAvailability;
import miu.edu.carfleet.dto.CarAvailabilityDto;
import miu.edu.carfleet.dto.CarsAvailabilityDto;

import java.util.ArrayList;
import java.util.List;

public class CarAvailabilityAdapter {
    public static CarAvailabilityDto getCarAvailabilityDtoFromCarAvailability(CarAvailability carInventory) {
        return new CarAvailabilityDto(carInventory.getId(), carInventory.getType(), carInventory.getBrand(), carInventory.getQuantity());
    }

    public static CarAvailability getCarAvailabilityFromCarAvailabilityDto(CarAvailabilityDto carInventoryDto) {
        return new CarAvailability(carInventoryDto.getId(), carInventoryDto.getType(), carInventoryDto.getBrand(), carInventoryDto.getQuantity());
    }

    public static CarsAvailabilityDto getCarsAvailabilityDtoFromCarAvailabilities(List<CarAvailability> carInventories) {
        List<CarAvailabilityDto> carInventoryDtos = new ArrayList<>();
        for (CarAvailability carInventory : carInventories)
            carInventoryDtos.add(getCarAvailabilityDtoFromCarAvailability(carInventory));
        return new CarsAvailabilityDto(carInventoryDtos);
    }
}
