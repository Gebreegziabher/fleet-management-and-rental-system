package miu.edu.carfleet.repository;

import miu.edu.carfleet.domain.CarAvailability;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CarAvailabilityRepositoryTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private ICarAvailabilityRepository carAvailabilityRepository;

    @Test
    public void whenFindByName_thenReturnEmployee() {
// given
        CarAvailability carAvailability = new CarAvailability("F1", "Ford", 40);
        mongoTemplate.save(carAvailability);

// when
        Optional<CarAvailability> found = carAvailabilityRepository.findByTypeAndBrand("F1", "Ford");

// then
        assertThat(found.get().getType(), equalTo("F1"));
        assertThat(found.get().getBrand(), equalTo("Ford"));

//cleanup
        carAvailabilityRepository.deleteById(found.get().getId());
    }
}
