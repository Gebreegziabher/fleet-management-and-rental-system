package miu.edu.carrental.repository;

import miu.edu.carrental.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRentalRepository extends JpaRepository<Rental, Long> {
    default Rental update(long id, Rental rental) {
        Optional<Rental> rentalOptional = findById(id);
        if (rentalOptional.isPresent()) {
            Rental updated = rentalOptional.get();
            updated.setReturnedDate(rental.getReturnedDate());
            return save(updated);
        }
        return null;
    }
}
