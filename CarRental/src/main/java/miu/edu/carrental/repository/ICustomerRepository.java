package miu.edu.carrental.repository;

import miu.edu.carrental.domain.CreditCard;
import miu.edu.carrental.domain.Customer;
import miu.edu.carrental.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByName(String name);

    List<Customer> findByEmail(String email);

    List<Customer> findByNameAndEmail(String name, String email);

    @Query("select c.creditCard from Customer c where c.customerNumber=:customerNumber")
    CreditCard findCreditCard(@Param("customerNumber") long customerNumber);

    @Query("select c.reservations from Customer c where c.customerNumber=:customerNumber")
    List<Reservation> findReservations(@Param("customerNumber") long customerNumber);

    default Customer update(long customerNumber, Customer customer) {
        Optional<Customer> saved = findById(customerNumber);
        if (saved.isPresent()) {
            Customer updated = saved.get();
            updated.setName(customer.getName());
            updated.setEmail(customer.getEmail());
            return save(updated);
        }
        return null;
    }
}
