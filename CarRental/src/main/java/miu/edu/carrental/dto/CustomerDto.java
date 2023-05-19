package miu.edu.carrental.dto;

import miu.edu.carrental.domain.Reservation;
import miu.edu.carrental.dto.reservation.ReservationDto;

import java.util.ArrayList;
import java.util.List;

public class CustomerDto {
    private long customerNumber;
    private String name;
    private String email;
    private CreditCardDto creditCard;
    private List<ReservationDto> reservations;

    public CustomerDto() {
    }

    public CustomerDto(long customerNumber, String name, String email) {
        this.customerNumber = customerNumber;
        this.name = name;
        this.email = email;
    }

    public CustomerDto(long customerNumber, String name, String email, CreditCardDto creditCard) {
        this.customerNumber = customerNumber;
        this.name = name;
        this.email = email;
        this.creditCard = creditCard;
    }

    public long getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(long customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CreditCardDto getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCardDto creditCard) {
        this.creditCard = creditCard;
    }

    public List<ReservationDto> getReservations() {
        return reservations;
    }

    public void setReservations(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerNumber=" + customerNumber +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", creditCard=" + creditCard +
                '}';
    }
}
