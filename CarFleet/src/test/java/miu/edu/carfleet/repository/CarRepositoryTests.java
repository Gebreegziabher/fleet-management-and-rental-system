package miu.edu.carfleet.repository;

import miu.edu.carfleet.common.Availability;
import miu.edu.carfleet.domain.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CarRepositoryTests {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ICarRepository customerRepository;
    @Test
    public void testFindById_thenReturnCar() {
        // Given
        Car car = new Car("TEST-PLATE", "Mustang", "Ford", 30000, Availability.AVAILABLE);
        mongoTemplate.save(car);

        // When
        Car found = customerRepository.findById(car.getLicensePlate()).get();

        // Then
        assertThat(found.getLicensePlate(), equalTo(car.getLicensePlate()));
        assertThat(found.getType(), equalTo(car.getType()));
        assertThat(found.getBrand(), equalTo(car.getBrand()));
        assertThat(found.getAvailability(), equalTo(car.getAvailability()));

        //cleanup
        customerRepository.deleteById(car.getLicensePlate());
    }
}
