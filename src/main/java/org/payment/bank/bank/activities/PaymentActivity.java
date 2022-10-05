package org.payment.bank.bank.activities;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import org.payment.bank.bank.dto.PaymentDto;

@ActivityInterface
public interface PaymentActivity {

    @ActivityMethod
    void payOut(PaymentDto paymentDto);

    @ActivityMethod
    void payIn(PaymentDto paymentDto);

    @ActivityMethod
    void fullPayment(PaymentDto paymentDto);
}
