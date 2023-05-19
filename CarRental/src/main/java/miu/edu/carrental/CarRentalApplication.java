package miu.edu.carrental;

import miu.edu.carrental.config.CarFleetConfig;
import miu.edu.carrental.config.CarRentalConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties({CarFleetConfig.class, CarRentalConfig.class})
public class CarRentalApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(CarRentalApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
