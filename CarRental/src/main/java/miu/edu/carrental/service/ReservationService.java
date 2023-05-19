package miu.edu.carrental.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import miu.edu.carrental.adapter.PaymentAdapter;
import miu.edu.carrental.adapter.RentalAdapter;
import miu.edu.carrental.common.Availability;
import miu.edu.carrental.common.CarAvailabilityInstruction;
import miu.edu.carrental.common.PaymentStatus;
import miu.edu.carrental.config.CarRentalConfig;
import miu.edu.carrental.domain.Customer;
import miu.edu.carrental.domain.Payment;
import miu.edu.carrental.domain.Rental;
import miu.edu.carrental.domain.Reservation;
import miu.edu.carrental.dto.CarDto;
import miu.edu.carrental.dto.reservation.RentalDto;
import miu.edu.carrental.dto.reservation.ReservationsDto;
import miu.edu.carrental.gateway.CarFleetGateway;
import miu.edu.carrental.integration.messaging.IJMSSender;
import miu.edu.carrental.repository.IReservationRepository;
import miu.edu.carrental.adapter.CustomerAdapter;
import miu.edu.carrental.adapter.ReservationAdapter;
import miu.edu.carrental.dto.reservation.ReservationDto;
import miu.edu.carrental.util.PaymentCalculatorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService implements IReservationService {
    @Autowired
    private IReservationRepository reservationRepository;

    @Autowired
    private CarFleetGateway carFleetGateway;

    @Autowired
    private CarRentalConfig carRentalConfig;

    @Autowired
    private IJMSSender jmsSender;

    private final Logger logger = LoggerFactory.getLogger(ReservationService.class);

    @Override
    public ReservationDto findById(long id) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            CarDto carDto = carFleetGateway.findByLicensePlate(reservation.getLicenseNumber());
            ReservationDto reservationDto = ReservationAdapter.getReservationDtoFromReservation(reservation);
            reservationDto.setCar(carDto);
            return reservationDto;
        }
        return null;
    }

    @Override
    public ReservationsDto findByLicensePlate(String licensePlate) {
        List<Reservation> reservations = reservationRepository.findByLicensePlate(licensePlate);
        return getReservationsDto(reservations);
    }

    private ReservationsDto getReservationsDto(List<Reservation> reservations) {
        List<ReservationDto> reservationDtos = new ArrayList<>();
        for (Reservation reservation : reservations) {
            CarDto carDto = carFleetGateway.findByLicensePlate(reservation.getLicenseNumber());
            ReservationDto reservationDto = ReservationAdapter.getReservationDtoFromReservation(reservation);
            reservationDto.setCar(carDto);
            reservationDtos.add(reservationDto);
        }
        return new ReservationsDto(reservationDtos);
    }

    @Override
    public ReservationsDto findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return getReservationsDto(reservations);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public ReservationDto reserve(ReservationDto reservationDto) throws JsonProcessingException {
        Reservation reservation = ReservationAdapter.getReservationFromReservationDto(reservationDto);

        //Set PAYMENT STATUS = PENDING
        Payment payment = new Payment(
                PaymentCalculatorUtil.getTotalPayment(
                        reservation.getStartingOn(),
                        reservation.getEndingOn(),
                        carRentalConfig.getReservationSetting().getPaymentAmountPerDay()),
                null,
                PaymentStatus.PENDING);
        reservation.setPayment(payment);
        reservationRepository.save(reservation);

        reservationDto.setPayment(PaymentAdapter.getPaymentDtoFromPayment(payment));
        reservationDto.setId(reservation.getId());

        CarAvailabilityInstruction instruction = new CarAvailabilityInstruction(reservation.getLicenseNumber(), Availability.RESERVED);
        ObjectMapper objectMapper = new ObjectMapper();
        String instAsString = objectMapper.writeValueAsString(instruction);

        logger.info("Message sent: " + instAsString);
        logger.trace("Car reserved - LICENSE PLATE = "+reservation.getLicenseNumber());
        jmsSender.sendJMSMessage(instAsString);

        return reservationDto;
    }

    @Override
    public RentalDto rentOut(long id, RentalDto rentalDto) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            reservation.setRental(RentalAdapter.getRentalFromRentalDto(rentalDto));
            reservationRepository.save(reservation);

            logger.trace("Car rented out - LICENSE PLATE = "+reservation.getLicenseNumber());

            return RentalAdapter.getRentalDtoFromRental(reservation.getRental());
        }
        return null;
    }

    @Override
    public RentalDto returnFromRent(long id, RentalDto rentalDto) throws JsonProcessingException {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            //1. Update RETURNED DATE, make PAYMENT
            Reservation reservation = reservationOptional.get();
            reservation.returnFromRent(rentalDto.getReturnProcessedBy(), rentalDto.getReturnedDate());
            reservation.updatePayment(new Date(), PaymentStatus.PAID);
            reservationRepository.save(reservation);

            //2. Send message to CAR FLEET SYSTEM to change AVAILABILITY of car
            CarAvailabilityInstruction instruction = new CarAvailabilityInstruction(reservationOptional.get().getLicenseNumber(), Availability.AVAILABLE);
            ObjectMapper objectMapper = new ObjectMapper();
            String instAsString = objectMapper.writeValueAsString(instruction);

            logger.info("Message sent: " + instAsString);
            logger.trace("Car returned - LICENSE PLATE = "+reservation.getLicenseNumber());
            jmsSender.sendJMSMessage(instAsString);

            return RentalAdapter.getRentalDtoFromRental(reservation.getRental());
        }
        return null;
    }

    @Override
    public ReservationDto update(long id, ReservationDto reservationDto) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        Customer customer = CustomerAdapter.getCustomerFromCustomerDto(reservationDto.getCustomer());
        Payment payment = PaymentAdapter.getPaymentFromPaymentDto(reservationDto.getPayment());
        Rental rental = RentalAdapter.getRentalFromRentalDto(reservationDto.getRental());
        if (reservationOptional.isPresent()) {
            Reservation reservation = reservationOptional.get();
            reservation.setCustomer(customer);
            reservation.setStartingOn(reservationDto.getStartingOn());
            reservation.setEndingOn(reservationDto.getEndingOn());
            reservation.setLicenseNumber(reservationDto.getCar().getLicensePlate());
            reservation.setPayment(payment);
            reservation.setRental(rental);
            return ReservationAdapter.getReservationDtoFromReservation(reservationRepository.save(reservation));
        }
        return null;
    }

    //CANCEL RESERVATION
    @Override
    public void deleteById(long id) throws JsonProcessingException {
        Optional<Reservation> reservationOptional = reservationRepository.findById(id);
        if (reservationOptional.isPresent()) {
            reservationRepository.deleteById(id);
            CarAvailabilityInstruction instruction = new CarAvailabilityInstruction(reservationOptional.get().getLicenseNumber(), Availability.DELETED);
            ObjectMapper objectMapper = new ObjectMapper();
            String instAsString = objectMapper.writeValueAsString(instruction);

            logger.info("Message sent: " + instAsString);
            jmsSender.sendJMSMessage(instAsString);
        }
    }
}
