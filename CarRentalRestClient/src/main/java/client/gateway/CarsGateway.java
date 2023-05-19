package client.gateway;

import client.config.CarRentalConfig;
import client.dto.CarDto;
import client.dto.CarsDto;
import client.dto.CustomersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class CarsGateway {
    @Autowired
    private CarRentalConfig carRentalConfig;
    RestTemplate restTemplate = new RestTemplate();
    private String serverUrl;

    @PostConstruct
    public void init() {
        serverUrl = carRentalConfig.getUrl();
    }

    public CarDto getCar(String licensePlate) {
        return restTemplate.getForObject(serverUrl + "/cars/{licensePlate}", CarDto.class);
    }

    public CarsDto getCars() {
        return restTemplate.getForObject(serverUrl + "/cars", CarsDto.class);
    }

    public CarsDto getCarReservations(String licenseNumber) {
        return restTemplate.getForObject(serverUrl + "/cars/" + licenseNumber + "/reservations", CarsDto.class);
    }
}
