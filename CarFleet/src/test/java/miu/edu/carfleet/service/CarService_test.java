package miu.edu.carfleet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import miu.edu.carfleet.domain.Car;
import miu.edu.carfleet.dto.CarDto;
import miu.edu.carfleet.event.CarAvailabilityChangedEvent;
import miu.edu.carfleet.repository.ICarRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.context.ApplicationEventPublisher;

public class CarService_test {

//    @Test
//    public void testSave() {
//        // arrange
//        CarDto carDto = new CarDto();
//        carDto.setLicensePlate("ABC123");
//        carDto.setBrand("Ford");
//        carDto.setType("Mustang");
//
//        Car savedCar = new Car();
//        savedCar.setLicensePlate("ABC123");
//        savedCar.setBrand("Ford");
//        savedCar.setType("Mustang");
//
//        ICarRepository mockRepository = mock(ICarRepository.class);
//        when(mockRepository.save(any(Car.class))).thenReturn(savedCar);
//
//        ApplicationEventPublisher mockEventPublisher = mock(ApplicationEventPublisher.class);
//        ArgumentCaptor<CarAvailabilityChangedEvent> eventCaptor = ArgumentCaptor.forClass(CarAvailabilityChangedEvent.class);
//
//        CarService carService = new CarService();
//        carService.setCarRepository(mockRepository);
//        carService.setEventPublisher(mockEventPublisher);
//
//        // act
//        CarDto result = carService.save(carDto);
//
//        // assert
//        assertEquals("ABC123", result.getLicensePlate());
//        assertEquals("Ford", result.getBrand());
//        assertEquals("Mustang", result.getType());
//
//        verify(mockRepository).save(any(Car.class));
//
//        verify(mockEventPublisher).publishEvent(eventCaptor.capture());
//        CarAvailabilityChangedEvent event = eventCaptor.getValue();
//        assertEquals("ABC123", event.getCarAvailabilityInstruction().getLicensePlate());
//        assertEquals(Availability.AVAILABLE, event.getInstruction().getAvailability());
//    }
}
