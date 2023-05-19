package miu.edu.carrental.controller;

import miu.edu.carrental.common.Availability;
import miu.edu.carrental.dto.CarDto;
import miu.edu.carrental.dto.CarsDto;
import miu.edu.carrental.dto.reservation.ReservationsDto;
import miu.edu.carrental.gateway.CarFleetGateway;
import miu.edu.carrental.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {
    @Autowired
    private CarFleetGateway carFleetGateway;

    @Autowired
    private IReservationService reservationService;

    @GetMapping("/cars/{licensePlate}")
    public ResponseEntity<?> findById(@PathVariable("licensePlate") String licensePlate) {
        CarDto carDto = carFleetGateway.findByLicensePlate(licensePlate);
        if (carDto != null)
            return new ResponseEntity<>(carDto, HttpStatus.OK);
        else
            return new ResponseEntity(new CustomErrorMessage("No car found with this license number."), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cars/{licensePlate}/reservations")
    public ResponseEntity<?> findReservationsById(@PathVariable("licensePlate") String licensePlate) {
        ReservationsDto reservationsDto = reservationService.findByLicensePlate(licensePlate);
        if (reservationsDto.getReservations().size() != 0)
            return new ResponseEntity<>(reservationsDto, HttpStatus.OK);
        return new ResponseEntity(new CustomErrorMessage("No reservation history found with this license number."), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/cars")
    public ResponseEntity<?> getAllOrfilter(@RequestParam(value = "type", required = false) String type,
                                            @RequestParam(value = "brand", required = false) String brand,
                                            @RequestParam(value = "price", required = false) Double price,
                                            @RequestParam(value = "availability", required = false) Availability availability) {
        CarsDto cars = carFleetGateway.filterOrFindAll(type, brand, price, availability);
        if (cars != null)
            return new ResponseEntity<>(cars, HttpStatus.OK);
        return new ResponseEntity(new CustomErrorMessage("No car found."), HttpStatus.NOT_FOUND);
    }
}
