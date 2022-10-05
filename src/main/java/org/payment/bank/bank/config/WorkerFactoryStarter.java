package org.payment.bank.bank.config;

import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import org.payment.bank.bank.activities.PaymentActivity;
import org.payment.bank.bank.config.properties.WorkerProperties;
import org.payment.bank.bank.config.properties.Workers;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import java.util.Map;

@RequiredArgsConstructor
public class WorkerFactoryStarter implements ApplicationListener<ContextRefreshedEvent> {

    private final Map<String, WorkerProperties> workerPropertiesMap;
    private final WorkerFactory workerFactory;
    private final PaymentActivity paymentActivity;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        setOrderWorker(workerPropertiesMap.get(Workers.BANK.name()));
        workerFactory.start();
    }

    private void setOrderWorker(WorkerProperties workerProperties) {
        Worker worker = workerFactory.newWorker(workerProperties.getQueueName());
        worker.registerActivitiesImplementations(paymentActivity);
    }
}
