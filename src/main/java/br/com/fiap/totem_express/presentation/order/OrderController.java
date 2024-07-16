package br.com.fiap.totem_express.presentation.order;

import br.com.fiap.totem_express.application.order.*;
import br.com.fiap.totem_express.domain.order.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.responses.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class OrderController {
    private OrderGatewayI orderGateway;

    public OrderController(OrderGatewayI orderGateway) {
        this.orderGateway = orderGateway;
    }

    @Operation(method = "/api/order/list", summary = "Lista pedidos do sistema")
    @GetMapping("/api/order/list")
    ResponseEntity<List<OrderView>> list() {
        List<OrderView> orders = orderGateway.findAll().stream().map(Order::toView).toList();
        return ResponseEntity.ok(orders);
    }
}
