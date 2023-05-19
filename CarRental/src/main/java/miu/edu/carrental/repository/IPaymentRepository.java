package miu.edu.carrental.repository;

import miu.edu.carrental.common.PaymentStatus;
import miu.edu.carrental.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    default Payment update(long id) {
        Optional<Payment> optionalPayment = findById(id);
        if (optionalPayment.isPresent()) {
            Payment updated = optionalPayment.get();
            updated.setPaymentDate(new Date());
            updated.setPaymentStatus(PaymentStatus.PAID);
            return save(updated);
        }
        return null;
    }
}
