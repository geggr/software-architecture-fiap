package br.com.fiap.totem_express.application.order;

import br.com.fiap.totem_express.presentation.order.OrderView;

import java.util.List;

public interface ListOrderUseCase {
    List<OrderView> execute();
}
