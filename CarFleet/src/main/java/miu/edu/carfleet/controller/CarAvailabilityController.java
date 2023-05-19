package miu.edu.carfleet.controller;

import miu.edu.carfleet.dto.CarsAvailabilityDto;
import miu.edu.carfleet.service.ICarAvailabilityService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarAvailabilityController {
    @Autowired
    private ICarAvailabilityService carAvailabilityService;

    @GetMapping("/caravailability")
    public ResponseEntity<?> findAll() {
        CarsAvailabilityDto carsAvailability = carAvailabilityService.findAll();
        if (carsAvailability.getCarsAvailability().size() != 0)
            return new ResponseEntity<>(carsAvailability, HttpStatus.OK);
        return new ResponseEntity<>(new CustomErrorMessage("No car availability data found"), HttpStatus.NOT_FOUND);
    }
}
