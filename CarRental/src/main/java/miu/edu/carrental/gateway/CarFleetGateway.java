package miu.edu.carrental.gateway;

import miu.edu.carrental.common.Availability;
import miu.edu.carrental.config.CarFleetConfig;
import miu.edu.carrental.dto.CarDto;
import miu.edu.carrental.dto.CarsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class CarFleetGateway {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private CarFleetConfig carFleetConfig;

    private String serverUrl;

    @PostConstruct
    public void init() {
        serverUrl = carFleetConfig.getUrl();
    }

    public CarDto findByLicensePlate(String licensePlate) {
        return restTemplate.getForObject(serverUrl + "cars/{licensePlate}", CarDto.class, licensePlate);
    }

    public CarsDto findAll() {
        return restTemplate.getForObject(serverUrl + "cars", CarsDto.class);
    }

    public CarsDto filterOrFindAll(String type, String brand, Double price, Availability availability) {
        String url = serverUrl + "cars?";
        if (type != null)
            url += "type=" + type;
        if (brand != null)
            url += "&&brand=" + brand;
        if (price != null)
            url += "&&price=" + price;
        if (availability != null)
            url += "&&availability=" + availability;

        return restTemplate.getForObject(url, CarsDto.class);
    }
}
