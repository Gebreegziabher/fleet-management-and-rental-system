package miu.edu.carfleet.repository;

import miu.edu.carfleet.common.Availability;
import miu.edu.carfleet.domain.Car;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICarRepository extends MongoRepository<Car, String> {
//    @Query("{ $and: [" +
//            "{$or: [ {type: :#{#type}}, {} ]}," +
//            "{$or: [ {brand: :#{#brand}}, {} ]}," +
//            //"{$or: [{price: :#{#price}}, {:#{#price}: {$exists: false}} ]}," +
//            "{$or: [{availability: :#{#availability}}, {} ]}" +
//            "]}")
//    List<Car> filter(@Param("type") String type,@Param("brand") String brand,@Param("price")Double price,@Param("availability")Availability availability);

    List<Car> findByType(String type);

    List<Car> findByBrand(String brand);

    List<Car> findByAvailability(Availability availability);

    List<Car> findByTypeOrAvailability(String type, Availability availability);

    List<Car> findByBrandOrAvailability(String brand, Availability availability);

    List<Car> findByPriceLessThanEqual(Double price);

    List<Car> findByTypeOrAvailabilityOrPriceLessThanEqual(String type, Availability availability, double price);

    List<Car> findByTypeOrBrandOrAvailabilityOrPriceLessThanEqual(String type, String brand, Availability availability, double price);

    List<Car> findByBrandOrAvailabilityOrPriceLessThanEqual(String brand, Availability availability, double price);

    List<Car> findByAvailabilityOrPriceLessThanEqual(Availability availability, double price);

    default Car update(String licensePlate, Car car) {
        Optional<Car> saved = findById(licensePlate);
        if (saved.isPresent()) {
            Car updated = saved.get();
            updated.setBrand(car.getBrand());
            updated.setType(car.getType());
            updated.setAvailability(car.getAvailability());
            updated.setPrice(car.getPrice());
            return save(updated);
        }
        return null;
    }
}
