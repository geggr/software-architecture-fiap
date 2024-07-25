package br.com.fiap.totem_express.application.product;

import java.util.List;
import java.util.Optional;

import br.com.fiap.totem_express.domain.product.Category;
import br.com.fiap.totem_express.domain.product.Product;

public interface ProductGateway {

    Product save(Product product);

    void delete(Long id);

    Optional<Product> findById(Long id);

    List<Product> findAllByCategory(Category category);
}
