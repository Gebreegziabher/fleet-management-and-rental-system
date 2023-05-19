package miu.edu.carrental.adapter;

import miu.edu.carrental.domain.CreditCard;
import miu.edu.carrental.domain.Customer;
import miu.edu.carrental.dto.CreditCardDto;
import miu.edu.carrental.dto.CustomerDto;
import miu.edu.carrental.dto.reservation.ReservationDto;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter {
    public static CustomerDto getCustomerDtoFromCustomer(Customer customer) {
        CustomerDto customerDto = new CustomerDto(customer.getCustomerNumber(), customer.getName(), customer.getEmail());
        if (customer.getCreditCard() != null) {
            CreditCardDto creditCardDto = CreditCardAdapter.getCreditCardDtoFromCreditCard(customer.getCreditCard());
            customerDto.setCreditCard(creditCardDto);
        }
//        if (customer.getReservations().size() != 0)
//            customerDto.setReservations(ReservationAdapter.getReservationDtosFromReservations(customer.getReservations()));
        return new CustomerDto(customer.getCustomerNumber(), customer.getName(), customer.getEmail());
    }

    public static Customer getCustomerFromCustomerDto(CustomerDto customerDto) {
        if (customerDto.getCreditCard() != null) {
            CreditCard creditCard = CreditCardAdapter.getCreditCardFromCreditCardDto(customerDto.getCreditCard());
            return new Customer(customerDto.getCustomerNumber(), customerDto.getName(), customerDto.getEmail(), creditCard);
        }
        return new Customer(customerDto.getCustomerNumber(), customerDto.getName(), customerDto.getEmail());
    }

    public static List<CustomerDto> getCustomerDtosFromCustomers(List<Customer> customers) {
        List<CustomerDto> customerDtos = new ArrayList<>();
        for (Customer customer : customers)
            customerDtos.add(getCustomerDtoFromCustomer(customer));
        return customerDtos;
    }

    public static List<Customer> getCustomersFromCustomerDtos(List<CustomerDto> customerDtos) {
        List<Customer> customers = new ArrayList<>();
        for (CustomerDto customerDto : customerDtos)
            customers.add(getCustomerFromCustomerDto(customerDto));
        return customers;
    }
}
