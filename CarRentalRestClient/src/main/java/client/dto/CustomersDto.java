package client.dto;

import java.util.List;

public class CustomersDto {
    private List<CustomerDto> customersDto;

    public CustomersDto(){}
    public CustomersDto(List<CustomerDto> customersDto) {
        this.customersDto = customersDto;
    }

    public void setCustomers(List<CustomerDto> customersDto) {
        this.customersDto = customersDto;
    }

    public List<CustomerDto> getCustomersDto() {
        return customersDto;
    }
}
