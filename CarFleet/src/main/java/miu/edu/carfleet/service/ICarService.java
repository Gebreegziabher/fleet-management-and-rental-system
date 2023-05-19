package miu.edu.carfleet.service;

import miu.edu.carfleet.common.Availability;
import miu.edu.carfleet.dto.CarDto;
import miu.edu.carfleet.dto.CarsDto;

import java.util.List;

public interface ICarService {
    CarDto findById(String licensePlate);
    CarsDto findAll();
    CarDto save(CarDto carDto);
    CarDto update(String licensePlate, CarDto carDto);
    void deleteById(String id);
    void deleteAll();
    CarsDto filter(String type, String brand, Double price, Availability availability);
}
