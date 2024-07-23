package br.com.fiap.totem_express.infrastructure.order;

import br.com.fiap.totem_express.application.order.*;
import br.com.fiap.totem_express.domain.order.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
public class OrderGatewayImpl implements OrderGateway {

    private final OrderRepository orderRepository;

    public OrderGatewayImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll().stream().map(OrderEntity::toDomain).toList();
    }
}
