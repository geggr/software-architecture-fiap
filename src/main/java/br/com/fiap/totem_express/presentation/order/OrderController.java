package br.com.fiap.totem_express.presentation.order;

import br.com.fiap.totem_express.application.order.CreateOrderUseCase;
import br.com.fiap.totem_express.application.order.ListOrderUseCase;
import br.com.fiap.totem_express.application.order.output.OrderView;
import br.com.fiap.totem_express.presentation.order.requests.CreateOrderRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class OrderController implements OrderDocumentation {

    private final ListOrderUseCase listOrderUseCase;
    private final CreateOrderUseCase createOrderUseCase;

    public OrderController(ListOrderUseCase listOrderUseCase, CreateOrderUseCase createOrderUseCase) {
        this.listOrderUseCase = listOrderUseCase;
        this.createOrderUseCase = createOrderUseCase;
    }

    @Override
    @GetMapping("/api/order/list")
    public ResponseEntity<List<OrderView>> list() {
        List<OrderView> orders = listOrderUseCase.execute();
        if(orders.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(orders);
    }

    @PostMapping("/api/order/create")
    public ResponseEntity create(@Valid @RequestBody CreateOrderRequest createOrderRequest) {
        OrderView execute = createOrderUseCase.execute(createOrderRequest);
        return ResponseEntity.status(201).body(execute);
    }
}
