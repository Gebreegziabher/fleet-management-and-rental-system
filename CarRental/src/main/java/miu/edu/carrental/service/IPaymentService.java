package miu.edu.carrental.service;

import miu.edu.carrental.dto.reservation.PaymentDto;

public interface IPaymentService {
    PaymentDto findById(long id);

    PaymentDto save(PaymentDto paymentDto);

    PaymentDto update(long id);
}
