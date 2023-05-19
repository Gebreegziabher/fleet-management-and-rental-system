package client.gateway;

import client.config.CarFleetConfig;
import client.dto.CarDto;
import client.dto.CarsAvailabilityDto;
import client.dto.CarsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class CarsAvailabilityGateway {
    @Autowired
    private CarFleetConfig carFleetConfig;
    RestTemplate restTemplate = new RestTemplate();
    private String serverUrl;

    @PostConstruct
    public void init() {
        serverUrl = carFleetConfig.getUrl();
    }

    public CarsAvailabilityDto getCars() {
        return restTemplate.getForObject(serverUrl + "/caravailability", CarsAvailabilityDto.class);
    }
}
