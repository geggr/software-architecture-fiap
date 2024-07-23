package br.com.fiap.totem_express.presentation.order;

import br.com.fiap.totem_express.application.order.ListOrderUseCaseImpl;
import br.com.fiap.totem_express.application.order.OrderGateway;
import br.com.fiap.totem_express.infrastructure.order.OrderGatewayImpl;
import br.com.fiap.totem_express.infrastructure.order.OrderRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfiguration {
    private final OrderRepository repository;

    public OrderConfiguration(OrderRepository repository) {
        this.repository = repository;
    }

    @Bean
    public OrderGateway orderGateway() {
        return new OrderGatewayImpl(repository);
    }

    @Bean
    public ListOrderUseCaseImpl listOrderUseCase() {
        return new ListOrderUseCaseImpl(orderGateway());
    }
}
