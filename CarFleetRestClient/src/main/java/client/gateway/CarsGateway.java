package client.gateway;

import client.config.CarFleetConfig;
import client.dto.CarDto;
import client.dto.CarsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class CarsGateway {
    @Autowired
    private CarFleetConfig carFleetConfig;
    RestTemplate restTemplate = new RestTemplate();
    private String serverUrl;

    @PostConstruct
    public void init() {
        serverUrl = carFleetConfig.getUrl();
    }

    public CarDto getCar(String licensePlate) {
        return restTemplate.getForObject(serverUrl + "/cars/{licensePlate}", CarDto.class);
    }

    public CarsDto getCars() {
        return restTemplate.getForObject(serverUrl + "/cars", CarsDto.class);
    }

    public void createCar(CarDto carDto) {
        restTemplate.postForLocation(serverUrl + "/cars", carDto);
    }
}
