package miu.edu.carfleet.repository;

import miu.edu.carfleet.common.Availability;
import miu.edu.carfleet.domain.CarAvailability;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface ICarAvailabilityRepository extends MongoRepository<CarAvailability, String> {
    Optional<CarAvailability> findByTypeAndBrand(String type, String brand);

    default CarAvailability saveOrUpdate(String brand, String type, Availability availability) {
        Optional<CarAvailability> saved = findByTypeAndBrand(type, brand);
        if (saved.isPresent()) {
            CarAvailability updated = saved.get();

            int incrementValue = switch (availability) {
                case RESERVED, DELETED -> -1;
                default -> 1;
            };

            updated.setQuantity(updated.getQuantity() + incrementValue);
            return save(updated);
        }
        return save(new CarAvailability(type, brand, 1));
    }
}
