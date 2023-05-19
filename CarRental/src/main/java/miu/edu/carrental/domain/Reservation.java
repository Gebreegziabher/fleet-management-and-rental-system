package miu.edu.carrental.domain;

import com.sun.istack.NotNull;
import miu.edu.carrental.common.PaymentStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Customer customer;
    private String licenseNumber;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date startingOn;

    @Temporal(TemporalType.DATE)
    @NotNull
    private Date endingOn;

    @ManyToOne(cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne(cascade = CascadeType.ALL)
    private Rental rental;

    public Reservation() {
    }

    public Reservation(long id, Date startingOn, Date endingOn, String licensePlate, Payment payment) {
        this.id = id;
        this.licenseNumber = licensePlate;
        this.startingOn = startingOn;
        this.endingOn = endingOn;
        this.payment = payment;
    }

    public Reservation(long id, Customer customer, String licenseNumber, Date from, Date to, Payment payment, Rental rental) {
        this.id = id;
        this.customer = customer;
        this.licenseNumber = licenseNumber;
        this.startingOn = from;
        this.endingOn = to;
        this.payment = payment;
        this.rental = rental;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
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

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void updatePayment(Date paymentDate, PaymentStatus paymentStatus) {
        payment.setPaymentDate(paymentDate);
        payment.setPaymentStatus(paymentStatus);
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public void rentOut(String rentingLocation, String rentOutProcessedBy, Date rentedOutDate) {
        rental.setRentingLocation(rentingLocation);
        rental.setRentOutProcessedBy(rentOutProcessedBy);
        rental.setRentedOutDate(rentedOutDate);
    }

    public void returnFromRent(String returnProcessedBy, Date returnedDate) {
        rental.setReturnProcessedBy(returnProcessedBy);
        rental.setReturnedDate(returnedDate);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "customer=" + customer +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", startingOn=" + startingOn +
                ", endingOn=" + endingOn +
                ", payment=" + payment +
                ", rental=" + rental +
                '}';
    }
}
