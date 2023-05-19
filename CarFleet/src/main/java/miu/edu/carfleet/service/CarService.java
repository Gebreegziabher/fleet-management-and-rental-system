package miu.edu.carfleet.service;

import miu.edu.carfleet.common.Availability;
import miu.edu.carfleet.common.CarAvailabilityInstruction;
import miu.edu.carfleet.domain.Car;
import miu.edu.carfleet.dto.CarsDto;
import miu.edu.carfleet.repository.ICarRepository;
import miu.edu.carfleet.dto.CarDto;
import miu.edu.carfleet.adapter.CarAdapter;
import miu.edu.carfleet.event.CarAvailabilityChangedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
@Transactional
public class CarService implements ICarService {
    @Autowired
    private ICarRepository carRepository;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public CarDto findById(String licensePlate) {
        Optional<Car> car = carRepository.findById(licensePlate);
        return car.map(CarAdapter::getCarDtoFromCar).orElse(null);
    }
//    public CarsDto filter(String type, String brand, Double price, Availability availability){
//        List<Car> cars = carRepository.filter(type, brand, price, availability);
//        return new CarsDto(CarAdapter.getCarDtosFromCars(cars));
//    }

    public CarsDto filter(String type, String brand, Double price, Availability availability) {
        List<Car> cars = null;
        if (type != null) {
            if (brand != null && price != null && availability != null)
                cars = carRepository.findByTypeOrBrandOrAvailabilityOrPriceLessThanEqual(type, brand, availability, price);
            else if (price != null && availability != null)
                cars = carRepository.findByTypeOrAvailabilityOrPriceLessThanEqual(type, availability, price);
            else if (availability != null)
                cars = carRepository.findByTypeOrAvailability(type, availability);
            else
                cars = carRepository.findByType(type);
        } else if (brand != null) {
            if (price != null && availability != null)
                cars = carRepository.findByBrandOrAvailabilityOrPriceLessThanEqual(brand, availability, price);
            else if (availability != null)
                cars = carRepository.findByBrandOrAvailability(brand, availability);
            else
                cars = carRepository.findByBrand(brand);
        } else if (price != null) {
            if (availability != null)
                cars = carRepository.findByAvailabilityOrPriceLessThanEqual(availability, price);
            else
                cars = carRepository.findByPriceLessThanEqual(price);
        } else if (availability != null)
            cars = carRepository.findByAvailability(availability);
        else
            cars = carRepository.findAll();

        return new CarsDto(CarAdapter.getCarDtosFromCars(cars));
    }

    @Override
    public CarsDto findAll() {
        List<Car> cars = carRepository.findAll();
        return new CarsDto(CarAdapter.getCarDtosFromCars(cars));
    }

    @Override
    public CarDto save(CarDto carDto) {
        Car car = CarAdapter.getCarFromCarDto(carDto);
        Car saved = carRepository.save(car);

        CarAvailabilityChangedEvent event = new CarAvailabilityChangedEvent(new CarAvailabilityInstruction(car.getLicensePlate(), Availability.AVAILABLE));
        eventPublisher.publishEvent(event);

        return CarAdapter.getCarDtoFromCar(saved);
    }

    @Override
    public CarDto update(String licensePlate, CarDto carDto) {
        Car car = CarAdapter.getCarFromCarDto(carDto);
        Car saved = carRepository.update(licensePlate, car);
        return CarAdapter.getCarDtoFromCar(saved);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void deleteById(String id) {
        try {
            Optional<Car> carOptional = carRepository.findById(id);
            if (carOptional.isPresent()) {
                Car car = carOptional.get();
                carRepository.deleteById(id);
                var inst = new CarAvailabilityInstruction(
                        car.getLicensePlate(),
                        car.getBrand(),
                        car.getType(),
                        Availability.DELETED);
                CarAvailabilityChangedEvent event = new CarAvailabilityChangedEvent(inst);
                eventPublisher.publishEvent(event);
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        long count = carRepository.count();
        carRepository.deleteAll();
    }
}
