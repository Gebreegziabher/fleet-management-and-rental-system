package client.gateway;

import client.config.CarRentalConfig;
import client.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class ReservationsGateway {
    @Autowired
    private CarRentalConfig carRentalConfig;
    RestTemplate restTemplate = new RestTemplate();
    private String serverUrl;

    @PostConstruct
    public void init() {
        serverUrl = carRentalConfig.getUrl();
    }

    public ReservationsDto getReservations() {
        return restTemplate.getForObject(serverUrl + "/reservations", ReservationsDto.class);
    }

    public void reserve(ReservationRequestDto reservationRequestDto) {
        restTemplate.postForLocation(serverUrl + "/reservations", reservationRequestDto);
    }

    public void rentOut(long id, RentalDto rentalDto) {
        restTemplate.postForLocation(serverUrl + "/reservations/{id}/rental", rentalDto, id);
    }

    public void returnAndPay(long id, RentalDto rentalDto) {
        restTemplate.put(serverUrl + "/reservations/{id}/rental", rentalDto, id);
    }
}
