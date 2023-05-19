package miu.edu.carrental.dto.reservation;

import java.util.List;

public class RentalsDto {
    private static List<RentalDto> rentals;
    public RentalsDto(List<RentalDto> rentals){
        this.rentals = rentals;
    }
}
