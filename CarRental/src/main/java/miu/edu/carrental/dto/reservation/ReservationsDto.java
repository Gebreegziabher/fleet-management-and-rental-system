package miu.edu.carrental.dto.reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationsDto {
    private List<ReservationDto> reservations = new ArrayList<>();
    public ReservationsDto(){}

    public ReservationsDto(List<ReservationDto> reservations) {
        this.reservations = reservations;
    }
    public List<ReservationDto> getReservations(){
        return reservations;
    }
}
