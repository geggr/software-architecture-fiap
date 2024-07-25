package br.com.fiap.totem_express.application.order;

import br.com.fiap.totem_express.domain.order.Order;
import br.com.fiap.totem_express.presentation.order.OrderView;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListOrderUseCaseImpl implements ListOrderUseCase {

    private final OrderGateway orderGateway;

    public ListOrderUseCaseImpl(OrderGateway orderGateway) {
        this.orderGateway = orderGateway;
    }

    @Override
    public List<OrderView> execute() {
        return orderGateway.findAll().stream().map(OrderView::new).toList();
    }
}
