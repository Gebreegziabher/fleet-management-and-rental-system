package miu.edu.carfleet.event;

import miu.edu.carfleet.common.CarAvailabilityInstruction;

public class CarAvailabilityChangedEvent {
    private CarAvailabilityInstruction carAvailabilityInstruction;

    public CarAvailabilityChangedEvent(CarAvailabilityInstruction carInventoryInstruction) {
        this.carAvailabilityInstruction = carInventoryInstruction;
    }
    public CarAvailabilityInstruction getCarAvailabilityInstruction() {
        return carAvailabilityInstruction;
    }
}
