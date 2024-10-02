package br.com.fiap.totem_express.presentation.payment;

import br.com.fiap.totem_express.application.payment.*;
import br.com.fiap.totem_express.application.payment.impl.CheckPaymentStatusUseCaseImpl;
import br.com.fiap.totem_express.application.payment.impl.ProcessPaymentWebhookUseCaseImpl;
import br.com.fiap.totem_express.infrastructure.payment.PaymentGatewayImpl;
import br.com.fiap.totem_express.infrastructure.payment.PaymentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfiguration {

    private final PaymentRepository repository;

    public PaymentConfiguration(PaymentRepository repository) {
        this.repository = repository;
    }

    @Bean
    public PaymentGateway paymentGateway() {
        return new PaymentGatewayImpl(repository);
    }

    @Bean
    public CheckPaymentStatusUseCase checkPaymentStatusUseCase() {
        return new CheckPaymentStatusUseCaseImpl(paymentGateway());
    }

    @Bean
    public ProcessPaymentWebhookUseCase processPaymentWebhookUseCase() {
        return new ProcessPaymentWebhookUseCaseImpl(paymentGateway());
    }
}
