package br.com.fiap.totem_express.presentation.order;

import br.com.fiap.totem_express.application.order.ListOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class OrderController implements OrderDocumentation {

    private final ListOrderUseCase listOrderUseCase;

    public OrderController(ListOrderUseCase listOrderUseCase) {
        this.listOrderUseCase = listOrderUseCase;
    }

    @Override
    @GetMapping("/api/order/list")
    public ResponseEntity<List<OrderView>> list() {
        List<OrderView> orders = listOrderUseCase.list();
        return ResponseEntity.ok(orders);
    }
}
