package miu.edu.carrental.service;

import miu.edu.carrental.dto.CreditCardDto;
import miu.edu.carrental.dto.CustomerDto;
import miu.edu.carrental.dto.CustomersDto;
import miu.edu.carrental.dto.reservation.ReservationsDto;

public interface ICustomerService {
    CustomerDto findById(long customerNumber);

    CustomersDto findByName(String name);

    CustomersDto findByEmail(String email);

    CustomersDto filterOrFindAll(String name, String email);

    CustomerDto save(CustomerDto customerDto);

    CustomerDto update(long customerNumber, CustomerDto customerDto);

    void deleteById(long customerNumber);

    CustomersDto findAll();

    CreditCardDto findCreditCard(Long customerNumber);
    ReservationsDto findReservations(Long customerNumber);
    CreditCardDto addCreditCard(Long customerNumber, CreditCardDto creditCard);
    void deleteCreditCardByCustomerNumber(Long customerNumber);
}
