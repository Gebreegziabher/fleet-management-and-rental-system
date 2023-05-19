package miu.edu.carfleet.service;

import miu.edu.carfleet.common.Availability;
import miu.edu.carfleet.domain.CarAvailability;
import miu.edu.carfleet.dto.CarDto;
import miu.edu.carfleet.dto.CarsAvailabilityDto;
import miu.edu.carfleet.integration.email.EmailSender;
import miu.edu.carfleet.integration.messaging.JMSListener;
import miu.edu.carfleet.repository.ICarAvailabilityRepository;
import miu.edu.carfleet.adapter.CarAvailabilityAdapter;
import miu.edu.carfleet.dto.CarAvailabilityDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarAvailabilityService implements ICarAvailabilityService {
    @Autowired
    private ICarAvailabilityRepository carInventoryRepository;

    @Autowired
    ICarService carService;

    @Autowired
    EmailSender emailSender;

    private Logger logger = LoggerFactory.getLogger(JMSListener.class);

    @Override
    public CarAvailabilityDto findById(String id) {
        Optional<CarAvailability> carInventory = carInventoryRepository.findById(id);
        return carInventory.map(CarAvailabilityAdapter::getCarAvailabilityDtoFromCarAvailability).orElse(null);
    }

    @Override
    public CarAvailabilityDto findByTypeAndBrand(String type, String brand) {
        Optional<CarAvailability> carInventory = carInventoryRepository.findByTypeAndBrand(type, brand);
        return carInventory.map(CarAvailabilityAdapter::getCarAvailabilityDtoFromCarAvailability).orElse(null);
    }

    @Override
    public CarAvailabilityDto saveOrUpdate(String licensePlate, String brand, String type, Availability availability) {
        CarDto carDto = carService.findById(licensePlate);
        if (carDto != null) {
            brand = carDto.getBrand();
            type = carDto.getType();
        }
        CarAvailability carInventory = carInventoryRepository.saveOrUpdate(brand, type, availability);
        CarAvailabilityDto carInventoryDto = CarAvailabilityAdapter.getCarAvailabilityDtoFromCarAvailability(carInventory);

        int brandTypeCount = carInventory.getQuantity();
        if (brandTypeCount < 3 && availability == Availability.RESERVED)
            emailSender.sendEmail("To inform you that count[" + brand + "/" + type + "] < " + brandTypeCount);

        return carInventoryDto;
    }

    @Override
    public CarsAvailabilityDto findAll() {
        List<CarAvailability> carAvailabilities = carInventoryRepository.findAll();
        return CarAvailabilityAdapter.getCarsAvailabilityDtoFromCarAvailabilities(carAvailabilities);
    }

    @Override
    public void deleteById(String id) {
        carInventoryRepository.deleteById(id);
    }
}
