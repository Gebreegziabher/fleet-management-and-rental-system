package miu.edu.carrental.adapter;

import miu.edu.carrental.domain.Customer;
import miu.edu.carrental.domain.Payment;
import miu.edu.carrental.domain.Rental;
import miu.edu.carrental.domain.Reservation;
import miu.edu.carrental.dto.CarDto;
import miu.edu.carrental.dto.CustomerDto;
import miu.edu.carrental.dto.reservation.PaymentDto;
import miu.edu.carrental.dto.reservation.RentalDto;
import miu.edu.carrental.dto.reservation.ReservationDto;
import miu.edu.carrental.dto.reservation.ReservationsDto;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter {
    public static ReservationDto getReservationDtoFromReservation(Reservation reservation) {
        CustomerDto customerDto = null;
        if (reservation.getCustomer() != null)
            customerDto = CustomerAdapter.getCustomerDtoFromCustomer(reservation.getCustomer());

        RentalDto rentalDto = null;
        if (reservation.getRental() != null)
            rentalDto = RentalAdapter.getRentalDtoFromRental(reservation.getRental());

        PaymentDto paymentDto = null;
        if (reservation.getPayment() != null)
            paymentDto = PaymentAdapter.getPaymentDtoFromPayment(reservation.getPayment());

        return new ReservationDto(reservation.getId(), reservation.getStartingOn(), reservation.getEndingOn(), customerDto, paymentDto, rentalDto);
    }

    public static Reservation getReservationFromReservationDto(ReservationDto reservationDto) {
        Customer customer = null;
        if (reservationDto.getCustomer() != null)
            customer = CustomerAdapter.getCustomerFromCustomerDto(reservationDto.getCustomer());

        Rental rental = null;
        if (reservationDto.getRental() != null)
            rental = RentalAdapter.getRentalFromRentalDto(reservationDto.getRental());

        Payment payment = null;
        if (reservationDto.getPayment() != null)
            payment = PaymentAdapter.getPaymentFromPaymentDto(reservationDto.getPayment());

        return new Reservation(reservationDto.getId(), customer, reservationDto.getCar().getLicensePlate(), reservationDto.getStartingOn(), reservationDto.getEndingOn(), payment, rental);
    }
    public static List<ReservationDto> getReservationDtosFromReservations(List<Reservation> reservations){
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for(Reservation reservation: reservations)
            reservationDtos.add(getReservationDtoFromReservation(reservation));
        return reservationDtos;
    }
    public static ReservationsDto getReservationsDtoFromReservations(List<Reservation> reservations){
        return new ReservationsDto(getReservationDtosFromReservations(reservations));
    }
}
