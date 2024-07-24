package br.com.fiap.totem_express.infrastructure.product;

import java.util.List;
import java.util.Optional;

import br.com.fiap.totem_express.application.product.ProductGateway;
import br.com.fiap.totem_express.domain.product.Category;
import br.com.fiap.totem_express.domain.product.Product;

public class ProductGatewayImpl implements ProductGateway {

    private final ProductRepository repository;

    public ProductGatewayImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = new ProductEntity(product);
        ProductEntity save = repository.save(productEntity);
        Product domain = save.toDomain();
        return domain;
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Product with id: " + id + " not found");
        }
        repository.deleteById(id);
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id).map(ProductEntity::toDomain);
    }

    @Override
    public List<Product> findAllByCategory(Category category) {
        return repository.findAllByCategory(category).stream().map(ProductEntity::toDomain).toList();
    }

}
