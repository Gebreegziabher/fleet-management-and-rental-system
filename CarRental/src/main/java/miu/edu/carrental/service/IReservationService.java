package miu.edu.carrental.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import miu.edu.carrental.dto.reservation.RentalDto;
import miu.edu.carrental.dto.reservation.ReservationDto;
import miu.edu.carrental.dto.reservation.ReservationsDto;

public interface IReservationService {
    ReservationDto findById(long id);

    ReservationsDto findAll();
    ReservationsDto findByLicensePlate(String licensePlate);

    ReservationDto reserve(ReservationDto reservationDto) throws JsonProcessingException;

    RentalDto rentOut(long id, RentalDto rentalDto);

    RentalDto returnFromRent(long id, RentalDto rentalDto) throws JsonProcessingException;

    ReservationDto update(long id, ReservationDto reservationDto);

    void deleteById(long id) throws JsonProcessingException;
}
