package br.com.fiap.totem_express.application.order;

import br.com.fiap.totem_express.domain.order.*;

import java.util.*;

public interface OrderGateway {
    List<Order> findAll();

    Order create(Order domain);
}
