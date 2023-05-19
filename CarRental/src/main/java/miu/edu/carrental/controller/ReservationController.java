package miu.edu.carrental.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import miu.edu.carrental.common.Availability;
import miu.edu.carrental.config.CarRentalConfig;
import miu.edu.carrental.dto.CarDto;
import miu.edu.carrental.dto.CreditCardDto;
import miu.edu.carrental.dto.CustomerDto;
import miu.edu.carrental.dto.reservation.RentalDto;
import miu.edu.carrental.dto.reservation.ReservationDto;
import miu.edu.carrental.dto.reservation.ReservationRequestDto;
import miu.edu.carrental.dto.reservation.ReservationsDto;
import miu.edu.carrental.gateway.CarFleetGateway;
import miu.edu.carrental.service.ICustomerService;
import miu.edu.carrental.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReservationController {
    @Autowired
    private IReservationService reservationService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private CarFleetGateway carFleetGateway;

    @Autowired
    private CarRentalConfig carRentalConfig;

    @GetMapping("/reservations/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        ReservationDto reservation = reservationService.findById(id);
        if (reservation != null)
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        return new ResponseEntity<>(new CustomErrorMessage("No reservation found."), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reservations")
    public ResponseEntity<?> get() {
        ReservationsDto reservations = reservationService.findAll();
        if (reservations != null)
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        return new ResponseEntity<>(new CustomErrorMessage("No reservation found."), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/reservations")
    public ResponseEntity<?> reserve(@RequestBody ReservationRequestDto reservationRequestDto) throws JsonProcessingException {
        long customerNumber = reservationRequestDto.getCustomerNumber();
        ReservationsDto reservationsDto = customerService.findReservations(customerNumber);
        int allowedReservationsForCustomer = carRentalConfig.getReservationSetting().getMaxAllowedReservations();
        if (reservationsDto.getReservations().size() <= allowedReservationsForCustomer) {
            //Customer Details
            CustomerDto customerDto = customerService.findById(customerNumber);

            if (customerDto != null) {
                CreditCardDto creditCardDto = customerService.findCreditCard(customerNumber);

                if (creditCardDto != null) {
                    //Car Details
                    CarDto carDto = carFleetGateway.findByLicensePlate(reservationRequestDto.getLicensePlate());
                    carDto.setAvailability(Availability.RESERVED);

                    ReservationDto reservation = new ReservationDto();
                    reservation.setCustomer(customerDto);
                    reservation.setStartingOn(reservationRequestDto.getStartingOn());
                    reservation.setEndingOn(reservationRequestDto.getEndingOn());
                    reservation.setCar(carDto);
                    return new ResponseEntity<>(reservationService.reserve(reservation), HttpStatus.OK);
                }
                return new ResponseEntity<>(new CustomErrorMessage("No credit card found for customer [" + customerNumber + "]"), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(new CustomErrorMessage("No customer found [" + customerNumber + "]"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new CustomErrorMessage("Limit reached(" + allowedReservationsForCustomer + "): too many reservations for customer [" + customerNumber + "]"), HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/reservations/{id}/rental")
    public ResponseEntity<?> rentOut(@PathVariable("id") Long id, @RequestBody RentalDto rentalDto) {
        ReservationDto reservationDto = reservationService.findById(id);
        if (reservationDto != null) {
            RentalDto rental = reservationService.rentOut(id, rentalDto);
            return new ResponseEntity<>(rental, HttpStatus.OK);
        }
        return new ResponseEntity<>(new CustomErrorMessage("Unable to process request, reservation not found."), HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reservations/{id}/rental")
    public ResponseEntity<?> returnAndPay(@PathVariable("id") Long id, @RequestBody RentalDto rentalDto) throws JsonProcessingException {
        RentalDto rental = reservationService.returnFromRent(id, rentalDto);
        if (rental != null)
            return new ResponseEntity<>(rental, HttpStatus.OK);
        return new ResponseEntity<>(new CustomErrorMessage("Unable to process request, rental not found."), HttpStatus.NOT_FOUND);
    }
}
