package miu.edu.carrental.repository;

import miu.edu.carrental.domain.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICreditCardRepository extends JpaRepository<CreditCard, String> {
}
