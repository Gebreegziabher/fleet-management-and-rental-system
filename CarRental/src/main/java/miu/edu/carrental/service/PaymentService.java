package miu.edu.carrental.service;

import miu.edu.carrental.adapter.PaymentAdapter;
import miu.edu.carrental.domain.Payment;
import miu.edu.carrental.dto.reservation.PaymentDto;
import miu.edu.carrental.repository.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService implements IPaymentService {
    @Autowired
    private IPaymentRepository paymentRepository;

    @Override
    public PaymentDto findById(long id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return payment.map(PaymentAdapter::getPaymentDtoFromPayment).orElse(null);
    }

    @Override
    public PaymentDto save(PaymentDto paymentDto) {
        Payment payment = PaymentAdapter.getPaymentFromPaymentDto(paymentDto);
        return PaymentAdapter.getPaymentDtoFromPayment(paymentRepository.save(payment));
    }

    @Override
    public PaymentDto update(long id) {
        return PaymentAdapter.getPaymentDtoFromPayment(paymentRepository.update(id));
    }
}
