package miu.edu.carfleet.service;

import miu.edu.carfleet.adapter.CarAdapter;
import miu.edu.carfleet.common.Availability;
import miu.edu.carfleet.domain.Car;
import miu.edu.carfleet.dto.CarDto;
import miu.edu.carfleet.event.CarAvailabilityChangedEvent;
import miu.edu.carfleet.repository.ICarRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@Component
public class CarServiceTests {

    @Mock
    private ICarRepository carRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @InjectMocks
    private CarService carService;


    @Test
    public void whenValidLicensePlate_thenCarShouldBeFound(){
        //Setup
        String licensePlate = "TS-111-111";
        Car testCar = new Car(licensePlate,"F1","Ford", 40000.0, Availability.AVAILABLE);
        Optional<Car> testCarOptional = Optional.of(testCar);
        Mockito.when(carRepository.findById(licensePlate)).thenReturn(testCarOptional);

        //when
        CarDto car = carService.findById(licensePlate);

        //then
        assertThat(car.getLicensePlate(), equalTo(licensePlate));
        assertThat(car.getBrand(), equalTo("Ford"));
        assertThat(car.getType(), equalTo("F1"));
    }

    @Test
    public void whenSaveCar_thenReturnTheSameCar(){
        // given
        String licensePlate = "TS-111-111";
        CarDto carDto = new CarDto(licensePlate,"F1","Ford", 40000.0, Availability.AVAILABLE);
        Car car = new Car(licensePlate,"F1","Ford", 40000.0, Availability.AVAILABLE);


        //CarAvailabilityChangedEvent event = new CarAvailabilityChangedEvent(carDto);

        // when
        Mockito.when(carRepository.save(car)).thenReturn(car);
        CarDto savedCarDto = carService.save(carDto);

        // then
        assertEquals(carDto, savedCarDto);
        Mockito.verify(carRepository).save(car);
        //Mockito.verify(eventPublisher).publishEvent(event);
    }
}
