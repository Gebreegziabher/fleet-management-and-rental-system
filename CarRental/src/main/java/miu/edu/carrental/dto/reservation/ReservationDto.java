package miu.edu.carrental.dto.reservation;

import miu.edu.carrental.dto.CarDto;
import miu.edu.carrental.dto.CustomerDto;

import java.util.Date;

public class ReservationDto {
    private long id;
    private Date startingOn;
    private Date endingOn;
    private CustomerDto customer;
    private RentalDto rental;
    private CarDto car;
    private PaymentDto payment;

    public ReservationDto() {
    }

    public ReservationDto(Long id, Date startingOn, Date endingOn, CustomerDto customerDto, PaymentDto paymentDto, RentalDto rentalDto) {
        this.id = id;
        this.customer = customerDto;
        this.startingOn = startingOn;
        this.endingOn = endingOn;
        this.payment = paymentDto;
        this.rental = rentalDto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartingOn() {
        return startingOn;
    }

    public void setStartingOn(Date startingOn) {
        this.startingOn = startingOn;
    }

    public Date getEndingOn() {
        return endingOn;
    }

    public void setEndingOn(Date endingOn) {
        this.endingOn = endingOn;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public CarDto getCar() {
        return car;
    }

    public void setCar(CarDto car) {
        this.car = car;
    }

    public PaymentDto getPayment() {
        return payment;
    }

    public void setPayment(PaymentDto payment) {
        this.payment = payment;
    }

    public RentalDto getRental() {
        return rental;
    }

    public void setRental(RentalDto rental) {
        this.rental = rental;
    }
}
