package client.gateway;

import client.config.CarRentalConfig;
import client.dto.CustomerDto;
import client.dto.CustomersDto;
import client.dto.ReservationsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class CustomersGateway {
    @Autowired
    private CarRentalConfig carRentalConfig;
    RestTemplate restTemplate = new RestTemplate();
    private String serverUrl;

    @PostConstruct
    public void init() {
        serverUrl = carRentalConfig.getUrl();
    }

    public CustomerDto getCustomer(long customerNumber) {
        return restTemplate.getForObject(serverUrl + "/customers/{customerNumber}", CustomerDto.class, customerNumber);
    }

    public CustomersDto getCustomers() {
        return restTemplate.getForObject(serverUrl + "/customers", CustomersDto.class);
    }

    public CustomersDto filterCustomers(String name, String email) {
        String path = "/customers";
        if (name != null)
            path += "&&name=" + name;
        if (email != null)
            path += "&&email=" + email;
        return restTemplate.getForObject(serverUrl + path, CustomersDto.class);
    }

    public ReservationsDto getCustomerReservations(long customerNumber) {
        return restTemplate.getForObject(serverUrl + "/customers/{customerNumber}/reservations", ReservationsDto.class, customerNumber);
    }

    public void createCustomer(CustomerDto customerDto) {
        restTemplate.postForLocation(serverUrl + "/customers", customerDto);
    }

    public void deleteCustomer(long customerNumber) {
        restTemplate.delete(serverUrl + "/customers/{customerNumber}", customerNumber);
    }

    public CustomerDto updateCustomer(long customerNumber, CustomerDto customerDto) {
        restTemplate.put(serverUrl + "/customers/{customerNumber}", customerDto, customerNumber);
        return getCustomer(customerNumber);
    }
}
