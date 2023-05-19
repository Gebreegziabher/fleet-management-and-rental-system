package miu.edu.carfleet.service;

import miu.edu.carfleet.common.Availability;
import miu.edu.carfleet.dto.CarAvailabilityDto;
import miu.edu.carfleet.dto.CarsAvailabilityDto;

public interface ICarAvailabilityService {
    CarAvailabilityDto findByTypeAndBrand(String type, String brand);
    CarAvailabilityDto findById(String id);
    CarAvailabilityDto saveOrUpdate(String licensePlate, String brand, String type, Availability availability);
    CarsAvailabilityDto findAll();
    void deleteById(String id);
}
