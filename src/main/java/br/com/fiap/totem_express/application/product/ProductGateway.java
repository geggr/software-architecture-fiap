package br.com.fiap.totem_express.application.product;

import br.com.fiap.totem_express.domain.product.Product;

public interface ProductGateway {
    Product save(Product product);
}
