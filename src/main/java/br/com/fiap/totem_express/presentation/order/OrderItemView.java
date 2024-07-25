package br.com.fiap.totem_express.presentation.order;

import java.math.*;

public record OrderItemView(String name, Long quantity, BigDecimal price) {
}
