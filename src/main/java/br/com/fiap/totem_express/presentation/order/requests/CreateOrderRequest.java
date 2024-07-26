package br.com.fiap.totem_express.presentation.order.requests;

import br.com.fiap.totem_express.application.order.input.CreateOrderInput;
import br.com.fiap.totem_express.application.order.input.OrderItemInput;
import br.com.fiap.totem_express.domain.order.Order;
import br.com.fiap.totem_express.domain.order.OrderItem;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public record CreateOrderRequest(
        Set<OrderItemRequest> orderItemsRequest
) implements CreateOrderInput {

    @Override
    public Set<OrderItemInput> orderItems() {
        return orderItemsRequest() == null? null : new HashSet<>(orderItemsRequest());
    }

    //TODO pegar user de verdade
    @Override
    public Optional<Object> getPossibleUser() {
        return Optional.empty();
    }

    //TODO pegar user de verdade
    @Override
    public Order toDomain(Set<OrderItem> orderItemsDomain) {
        return new Order(
                orderItemsDomain,
                null
        );
    }
}
