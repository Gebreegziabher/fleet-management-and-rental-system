package miu.edu.carfleet.event;

import miu.edu.carfleet.dto.CarDto;
import miu.edu.carfleet.service.ICarAvailabilityService;
import miu.edu.carfleet.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CarAvailabilityChangedEventListener {
    @Autowired
    private ICarAvailabilityService carInventoryService;

    @Autowired
    private ICarService carService;

    @EventListener
    public void onEvent(CarAvailabilityChangedEvent event) {
        carInventoryService.saveOrUpdate(
                event.getCarAvailabilityInstruction().getLicencePlate(),
                event.getCarAvailabilityInstruction().getBrand(),
                event.getCarAvailabilityInstruction().getType(),
                event.getCarAvailabilityInstruction().getAvailability());

        CarDto carDto = carService.findById(event.getCarAvailabilityInstruction().getLicencePlate());
        carDto.setAvailability(event.getCarAvailabilityInstruction().getAvailability());

        carService.update(event.getCarAvailabilityInstruction().getLicencePlate(), carDto);
    }
}
