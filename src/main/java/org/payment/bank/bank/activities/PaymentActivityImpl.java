package org.payment.bank.bank.activities;

import org.payment.bank.bank.dto.PaymentDto;
import org.payment.bank.bank.persistance.Operation;
import org.payment.bank.bank.persistance.OperationRepository;
import org.payment.bank.bank.persistance.OperationType;

public class PaymentActivityImpl implements PaymentActivity {

    private final OperationRepository operationRepository;

    public PaymentActivityImpl(OperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public void payOut(PaymentDto paymentDto) {
        Operation operation = new Operation();
        operation.setType(OperationType.PAY_OUT);
        operation.setAmount(paymentDto.getAmount());
        operation.setCurrency(paymentDto.getCurrency());
        operation.setAccountId(paymentDto.getFromAccountId());

        operationRepository.save(operation);
    }

    @Override
    public void payIn(PaymentDto paymentDto) {
        Operation operation = new Operation();
        operation.setType(OperationType.PAY_IN);
        operation.setAmount(paymentDto.getAmount());
        operation.setCurrency(paymentDto.getCurrency());
        operation.setAccountId(paymentDto.getToAccountId());

        operationRepository.save(operation);
    }
}
