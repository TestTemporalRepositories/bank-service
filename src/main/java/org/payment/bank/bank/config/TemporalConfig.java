package org.payment.bank.bank.config;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.WorkerFactory;
import lombok.AllArgsConstructor;
import org.payment.bank.bank.activities.PaymentActivity;
import org.payment.bank.bank.activities.PaymentActivityImpl;
import org.payment.bank.bank.config.properties.TemporalProperties;
import org.payment.bank.bank.persistance.OperationRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@AllArgsConstructor
public class TemporalConfig {

    private final TemporalProperties temporalProperties;

    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newServiceStubs(
                WorkflowServiceStubsOptions.newBuilder()
                        .setTarget(temporalProperties.getServer())
                        .build()
        );
    }

    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
        return WorkflowClient.newInstance(workflowServiceStubs,
                WorkflowClientOptions.newBuilder()
                        .setNamespace(temporalProperties.getNamespace())
                        .build()
        );
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient workflowClient) {
        return WorkerFactory.newInstance(workflowClient);
    }

    @Bean
    public PaymentActivity paymentActivity(OperationRepository operationRepository) {
        return new PaymentActivityImpl(operationRepository);
    }

    @Bean
    public WorkerFactoryStarter workerFactoryStarter(
            WorkerFactory workerFactory,
            PaymentActivity paymentActivity
    ) {
        return new WorkerFactoryStarter(
                temporalProperties.getWorkers(),
                workerFactory,
                paymentActivity
        );
    }
}
