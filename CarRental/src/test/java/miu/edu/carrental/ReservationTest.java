package miu.edu.carrental;

import miu.edu.carrental.common.PaymentStatus;
import miu.edu.carrental.domain.Payment;
import miu.edu.carrental.domain.Rental;
import miu.edu.carrental.domain.Reservation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

public class ReservationTest {

    @Test
    public void testUpdatePayment() {
        Payment payment = new Payment(100.0, new Date(), PaymentStatus.PENDING);

        Reservation reservation = new Reservation();
        reservation.setPayment(payment);

        Date paymentDate = new Date();
        PaymentStatus paymentStatus = PaymentStatus.PAID;
        reservation.updatePayment(paymentDate, paymentStatus);

        assertEquals(paymentDate, payment.getPaymentDate());
        assertEquals(paymentStatus, payment.getPaymentStatus());
    }

    @Test
    public void testRentOut() {
        Rental rental = new Rental();

        Reservation reservation = new Reservation();
        reservation.setRental(rental);

        String rentingLocation = "New York";
        String rentOutProcessedBy = "John Doe";
        Date rentedOutDate = new Date();
        reservation.rentOut(rentingLocation, rentOutProcessedBy, rentedOutDate);

        assertEquals(rentingLocation, rental.getRentingLocation());
        assertEquals(rentOutProcessedBy, rental.getRentOutProcessedBy());
        assertEquals(rentedOutDate, rental.getRentedOutDate());
    }

    @Test
    public void testReturnFromRent() {
        Rental rental = new Rental();

        Reservation reservation = new Reservation();
        reservation.setRental(rental);

        String returnProcessedBy = "Jane Doe";
        Date returnedDate = new Date();
        reservation.returnFromRent(returnProcessedBy, returnedDate);

        assertEquals(returnProcessedBy, rental.getReturnProcessedBy());
        assertEquals(returnedDate, rental.getReturnedDate());
    }
}