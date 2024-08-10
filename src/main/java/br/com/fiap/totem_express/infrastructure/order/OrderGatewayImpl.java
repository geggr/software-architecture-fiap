package br.com.fiap.totem_express.infrastructure.order;

import br.com.fiap.totem_express.application.order.*;
import br.com.fiap.totem_express.domain.order.*;
import org.springframework.stereotype.*;

import java.util.*;

public class OrderGatewayImpl implements OrderGateway {

    private final OrderRepository orderRepository;

    public OrderGatewayImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAllWithDeleteProducts().stream().map(orderEntity -> {
            Order orderDomain = orderEntity.toDomain();
            orderEntity.getItems().forEach(itemEntity -> {
               orderDomain.addItem(itemEntity.toDomain());
            });
            return orderDomain;
        }).toList();
    }

    @Override
    public Order create(Order domain) {
        OrderEntity savedOrderEntity = orderRepository.save(new OrderEntity(domain));
        Order savedOrderDomain = savedOrderEntity.toDomain();
        savedOrderEntity.getItems().forEach(orderItemEntity -> {
            savedOrderDomain.addItem(orderItemEntity.toDomain());
        });
        return savedOrderDomain;
    }

}
