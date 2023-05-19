package miu.edu.carrental.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {
    @Id
    private long customerNumber;
    private String name;
    private String email;

    @ManyToOne (cascade={CascadeType.ALL})
    private CreditCard creditCard;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<Reservation> reservations = new ArrayList<>();

    public Customer() {
    }

    public Customer(long customerNumber, String name, String email) {
        this.customerNumber = customerNumber;
        this.name = name;
        this.email = email;
    }

    public Customer(long customerNumber, String name, String email, CreditCard creditCard) {
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

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
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
