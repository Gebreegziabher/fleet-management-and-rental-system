package miu.edu.carfleet.controller;

import miu.edu.carfleet.common.Availability;
import miu.edu.carfleet.dto.CarDto;
import miu.edu.carfleet.dto.CarsDto;
import miu.edu.carfleet.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping("/cars/{licensePlate}")
    public ResponseEntity<?> findById(@PathVariable("licensePlate") String licensePlate) {
        CarDto carDto = carService.findById(licensePlate);
        if (carDto != null)
            return new ResponseEntity<>(carDto, HttpStatus.OK);
        else
            return new ResponseEntity<>(new CustomErrorMessage("No car found with this license number."), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cars")
    public ResponseEntity<?> getAllOrfilter(@RequestParam(value = "type", required = false) String type,
                                            @RequestParam(value = "brand", required = false) String brand,
                                            @RequestParam(value = "price", required = false) Double price,
                                            @RequestParam(value = "availability", required = false) Availability availability) {
        CarsDto cars = carService.filter(type, brand, price, availability);
        if (cars != null)
            return new ResponseEntity<>(cars, HttpStatus.OK);
        return new ResponseEntity<>(new CustomErrorMessage("No car found."), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/cars")
    public ResponseEntity<?> create(@RequestBody CarDto car) {
        CarDto existing = carService.findById(car.getLicensePlate());
        if (existing == null)
            return new ResponseEntity<>(carService.save(car), HttpStatus.CREATED);
        return new ResponseEntity<>(new CustomErrorMessage("Car exists with this license plate number."), HttpStatus.CONFLICT);
    }

    @PutMapping("/cars/{licensePlate}")
    public ResponseEntity<?> update(@PathVariable("licensePlate") String licensePlate, @RequestBody CarDto car) {
        CarDto saved = carService.update(licensePlate, car);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/cars/{licensePlate}")
    public ResponseEntity<?> delete(@PathVariable("licensePlate") String licensePlate) {
        carService.deleteById(licensePlate);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/cars")
    public ResponseEntity<?> deleteAll() {
        carService.deleteAll();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
