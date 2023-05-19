package miu.edu.carrental.controller;

import miu.edu.carrental.dto.reservation.ReservationsDto;
import miu.edu.carrental.service.ICustomerService;
import miu.edu.carrental.dto.CreditCardDto;
import miu.edu.carrental.dto.CustomerDto;
import miu.edu.carrental.dto.CustomersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/customers/{customerNumber}")
    public ResponseEntity<?> get(@PathVariable("customerNumber") Long customerNumber) {
        CustomerDto customerDto = customerService.findById(customerNumber);
        if (customerDto != null)
            return new ResponseEntity<>(customerDto, HttpStatus.OK);
        else
            return new ResponseEntity(new CustomErrorMessage("No customer found with this customer number."), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/customers")
    public ResponseEntity<?> getAllOrfilter(@RequestParam(value = "name", required = false) String name,
                                            @RequestParam(value = "email", required = false) String email) {
        CustomersDto customers = customerService.filterOrFindAll(name, email);
        if (customers != null)
            return new ResponseEntity<>(customers, HttpStatus.OK);
        return new ResponseEntity<>(new CustomErrorMessage("No customer found."), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/customers")
    public ResponseEntity<?> create(@RequestBody CustomerDto customerDto) {
        CustomerDto existing = customerService.findById(customerDto.getCustomerNumber());
        if (existing == null)
            return new ResponseEntity<>(customerService.save(customerDto), HttpStatus.CREATED);
        return new ResponseEntity<>(new CustomErrorMessage("Customer exists with this customer number."), HttpStatus.CONFLICT);
    }

    @PutMapping("/customers/{customerNumber}")
    public ResponseEntity<?> update(@PathVariable("customerNumber") Long customerNumber,
                                    @RequestBody CustomerDto customerDto) {
        CustomerDto saved = customerService.update(customerNumber, customerDto);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/customers/{customerNumber}")
    public ResponseEntity<?> delete(@PathVariable("customerNumber") Long customerNumber) {
        customerService.deleteById(customerNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customers/{customerNumber}/reservations")
    public ResponseEntity<?> getReservations(@PathVariable("customerNumber") Long customerNumber) {
        ReservationsDto reservations = customerService.findReservations(customerNumber);
        if (reservations != null)
            return new ResponseEntity<>(reservations, HttpStatus.OK);
        else
            return new ResponseEntity<>(new CustomErrorMessage("No reservation found for this customer."), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/customers/{customerNumber}/creditcard")
    public ResponseEntity<?> getCreditCard(@PathVariable("customerNumber") Long customerNumber) {
        CreditCardDto creditCardDto = customerService.findCreditCard(customerNumber);
        if (creditCardDto != null)
            return new ResponseEntity<>(creditCardDto, HttpStatus.OK);
        else
            return new ResponseEntity<>(new CustomErrorMessage("No credit cards found for this customer."), HttpStatus.NOT_FOUND);
    }

    @PostMapping("/customers/{customerNumber}/creditcard")
    public ResponseEntity<?> createCreditCard(@PathVariable("customerNumber") Long customerNumber,
                                              @RequestBody CreditCardDto creditCardDto) {
        CreditCardDto saved = customerService.addCreditCard(customerNumber, creditCardDto);
        if (saved != null) {
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(new CustomErrorMessage("This customer already has credit card."), HttpStatus.CONFLICT);
    }

    @DeleteMapping("/customers/{customerNumber}/creditcard")
    public ResponseEntity<?> deleteCreditCard(@PathVariable("customerNumber") Long customerNumber) {
        customerService.deleteCreditCardByCustomerNumber(customerNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
