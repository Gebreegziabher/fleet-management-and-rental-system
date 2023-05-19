package miu.edu.carrental.service;

import miu.edu.carrental.adapter.ReservationAdapter;
import miu.edu.carrental.domain.CreditCard;
import miu.edu.carrental.domain.Customer;
import miu.edu.carrental.domain.Reservation;
import miu.edu.carrental.dto.CarDto;
import miu.edu.carrental.dto.reservation.ReservationDto;
import miu.edu.carrental.dto.reservation.ReservationsDto;
import miu.edu.carrental.gateway.CarFleetGateway;
import miu.edu.carrental.repository.ICreditCardRepository;
import miu.edu.carrental.repository.ICustomerRepository;
import miu.edu.carrental.adapter.CreditCardAdapter;
import miu.edu.carrental.adapter.CustomerAdapter;
import miu.edu.carrental.dto.CreditCardDto;
import miu.edu.carrental.dto.CustomerDto;
import miu.edu.carrental.dto.CustomersDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private CarFleetGateway carFleetGateway;

    @Autowired
    private ICreditCardRepository creditCardRepository;

    private final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Override
    public CustomerDto findById(long customerNumber) {
        Optional<Customer> customer = customerRepository.findById(customerNumber);
        return customer.map(CustomerAdapter::getCustomerDtoFromCustomer).orElse(null);
    }

    @Override
    public CustomersDto filterOrFindAll(String name, String email) {
        List<Customer> customers;
        if (name != null && email != null) {
            customers = customerRepository.findByNameAndEmail(name, email);
        } else if (name != null) {
            customers = customerRepository.findByName(name);
        } else if (email != null) {
            customers = customerRepository.findByEmail(email);
        } else {
            customers = customerRepository.findAll();
        }
        return new CustomersDto(CustomerAdapter.getCustomerDtosFromCustomers(customers));
    }

    @Override
    public CustomersDto findByName(String name) {
        List<Customer> customers = customerRepository.findByName(name);
        return new CustomersDto(CustomerAdapter.getCustomerDtosFromCustomers(customers));
    }

    @Override
    public CustomersDto findByEmail(String email) {
        List<Customer> customers = customerRepository.findByEmail(email);
        return new CustomersDto(CustomerAdapter.getCustomerDtosFromCustomers(customers));
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = CustomerAdapter.getCustomerFromCustomerDto(customerDto);
        Customer saved = customerRepository.save(customer);

        logger.trace("Customer created: CUSTOMER_NUMBER = "+customer.getCustomerNumber());
        return CustomerAdapter.getCustomerDtoFromCustomer(saved);
    }

    @Override
    public CustomerDto update(long customerNumber, CustomerDto customerDto) {
        Customer customer = CustomerAdapter.getCustomerFromCustomerDto(customerDto);
        Customer saved = customerRepository.update(customerNumber, customer);

        logger.trace("Customer Updated: CUSTOMER_NUMBER = "+customer.getCustomerNumber());
        return CustomerAdapter.getCustomerDtoFromCustomer(saved);
    }

    @Override
    public void deleteById(long customerNumber) {
        logger.trace("Customer deleted: CUSTOMER_NUMBER = "+customerNumber);
        customerRepository.deleteById(customerNumber);
    }

    @Override
    public CustomersDto findAll() {
        List<Customer> customers = customerRepository.findAll();
        return new CustomersDto(CustomerAdapter.getCustomerDtosFromCustomers(customers));
    }

    @Override
    public CreditCardDto findCreditCard(Long customerNumber) {
        CreditCard creditCard = customerRepository.findCreditCard(customerNumber);
        if (creditCard != null)
            return CreditCardAdapter.getCreditCardDtoFromCreditCard(creditCard);
        return null;
    }

    @Override
    public ReservationsDto findReservations(Long customerNumber) {
        List<Reservation> reservations = customerRepository.findReservations(customerNumber);
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for(Reservation reservation: reservations) {
            CarDto carDto = carFleetGateway.findByLicensePlate(reservation.getLicenseNumber());
            ReservationDto reservationDto = ReservationAdapter.getReservationDtoFromReservation(reservation);
            reservationDto.setCar(carDto);
            reservationDtos.add(reservationDto);
        }
        return new ReservationsDto(reservationDtos);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public CreditCardDto addCreditCard(Long customerNumber, CreditCardDto creditCardDto) {
        CreditCard existing = customerRepository.findCreditCard(customerNumber);
        if (existing == null) {
            try {
                CreditCard creditCard = CreditCardAdapter.getCreditCardFromCreditCardDto(creditCardDto);
                creditCardRepository.save(creditCard);
                Customer customer = customerRepository.findById(customerNumber).get();
                customer.setCreditCard(creditCard);
                customerRepository.update(customerNumber, customer);

                logger.trace("Credit card added for customer: CUSTOMER_NUMBER = "+customerNumber);
                return CreditCardAdapter.getCreditCardDtoFromCreditCard(creditCard);
            } catch (Exception ex) {
                logger.trace(ex.getMessage());
                throw ex;
            }
        }
        return null;
    }

    public void deleteCreditCardByCustomerNumber(Long customerNumber) {
        CreditCard creditCard = customerRepository.findCreditCard(customerNumber);
        Customer customer = customerRepository.findById(customerNumber).get();
        customer.setCreditCard(null);
        customerRepository.update(customerNumber, customer);
        creditCardRepository.delete(creditCard);
    }
}
