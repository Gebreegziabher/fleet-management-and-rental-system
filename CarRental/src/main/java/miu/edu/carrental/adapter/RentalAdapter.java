package miu.edu.carrental.adapter;

import miu.edu.carrental.domain.Rental;
import miu.edu.carrental.dto.reservation.RentalDto;
import miu.edu.carrental.dto.reservation.RentalsDto;

import java.util.ArrayList;
import java.util.List;

public class RentalAdapter {
    public static RentalDto getRentalDtoFromRental(Rental rental) {
        return new RentalDto(rental.getId(), rental.getRentingLocation(), rental.getRentOutProcessedBy(), rental.getReturnProcessedBy(), rental.getRentedOutDate(), rental.getReturnedDate());
    }

    public static Rental getRentalFromRentalDto(RentalDto rentalDto) {
        return new Rental(rentalDto.getId(), rentalDto.getRentalLocation(), rentalDto.getRentOutProcessedBy(), rentalDto.getReturnProcessedBy(), rentalDto.getRentedOutDate(), rentalDto.getReturnedDate());
    }

    public static RentalsDto getRentalsDtoFromRentals(List<Rental> rentals) {
        List<RentalDto> rentalDtos = new ArrayList<>();
        for (Rental rental : rentals)
            rentalDtos.add(getRentalDtoFromRental(rental));
        return new RentalsDto(rentalDtos);
    }
}
