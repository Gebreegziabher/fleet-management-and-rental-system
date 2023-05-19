package miu.edu.carrental.dto;

import java.util.ArrayList;
import java.util.List;

public class CustomersDto {
    private List<CustomerDto> customers;

    public CustomersDto(List<CustomerDto> customers) {
        this.customers = customers;
    }

    public List<CustomerDto> getCustomersDto() {
        return customers;
    }
}
