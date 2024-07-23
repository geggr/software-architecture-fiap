package br.com.fiap.totem_express.presentation.order;

import br.com.fiap.totem_express.application.order.*;
import io.swagger.v3.oas.annotations.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class OrderController {
    private ListOrderUseCase listOrderUseCase;

    public OrderController(ListOrderUseCase listOrderUseCase) {
        this.listOrderUseCase = listOrderUseCase;
    }

    @Operation(method = "/api/order/list", summary = "Lista pedidos do sistema")
    @GetMapping("/api/order/list")
    ResponseEntity<List<OrderView>> list() {
        List<OrderView> orders = listOrderUseCase.list();
        return ResponseEntity.ok(orders);
    }
}
