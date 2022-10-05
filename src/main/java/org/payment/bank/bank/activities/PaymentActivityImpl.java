package org.payment.bank.bank.activities;

import org.payment.bank.bank.dto.PaymentDto;
import org.payment.bank.bank.persistance.Operation;
import org.payment.bank.bank.persistance.OperationRepository;
import org.payment.bank.bank.persistance.OperationType;

import java.util.List;

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

    @Override
    public void fullPayment(PaymentDto paymentDto) {
        Operation operationOut = new Operation();
        operationOut.setType(OperationType.PAY_OUT);
        operationOut.setAmount(paymentDto.getAmount());
        operationOut.setCurrency(paymentDto.getCurrency());
        operationOut.setAccountId(paymentDto.getFromAccountId());

        Operation operationIn = new Operation();
        operationIn.setType(OperationType.PAY_IN);
        operationIn.setAmount(paymentDto.getAmount());
        operationIn.setCurrency(paymentDto.getCurrency());
        operationIn.setAccountId(paymentDto.getToAccountId());

        operationRepository.saveAll(List.of(operationOut, operationIn));
    }
}
