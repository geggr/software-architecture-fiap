package br.com.fiap.totem_express.presentation.payment;

import br.com.fiap.totem_express.application.payment.PaymentGateway;
import br.com.fiap.totem_express.application.payment.impl.CheckPaymentStatusUseCase;
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
    public CheckPaymentStatusUseCase xpto() {
        return new CheckPaymentStatusUseCase(paymentGateway());
    }
}
