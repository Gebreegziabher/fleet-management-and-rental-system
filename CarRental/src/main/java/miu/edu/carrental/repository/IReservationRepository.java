package miu.edu.carrental.repository;

import miu.edu.carrental.domain.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IReservationRepository extends JpaRepository<Reservation, Long> {
    @Query("select r from Reservation r where r.licenseNumber=:licensePlate")
    List<Reservation> findByLicensePlate(@Param("licensePlate") String licensePlate);
}
