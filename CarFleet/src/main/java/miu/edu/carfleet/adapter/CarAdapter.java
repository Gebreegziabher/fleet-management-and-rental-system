package miu.edu.carfleet.adapter;

import miu.edu.carfleet.domain.Car;
import miu.edu.carfleet.dto.CarDto;

import java.util.ArrayList;
import java.util.List;

public class CarAdapter {
    public static CarDto getCarDtoFromCar(Car car) {
        return new CarDto(car.getLicensePlate(), car.getType(), car.getBrand(), car.getPrice(), car.getAvailability());
    }

    public static Car getCarFromCarDto(CarDto carDto) {
        return new Car(carDto.getLicensePlate(), carDto.getType(), carDto.getBrand(), carDto.getPrice(), carDto.getAvailability());
    }

    public static List<CarDto> getCarDtosFromCars(List<Car> cars) {
        List<CarDto> carDtos = new ArrayList<>();
        for (Car car : cars)
            carDtos.add(getCarDtoFromCar(car));
        return carDtos;
    }

    public static List<Car> getCarsFromCarDtos(List<CarDto> carDtos) {
        List<Car> cars = new ArrayList<>();
        for (CarDto carDto : carDtos)
            cars.add(getCarFromCarDto(carDto));
        return cars;
    }
}
