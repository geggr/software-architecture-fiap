package br.com.fiap.totem_express.infrastructure.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.totem_express.domain.product.Category;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
    List<ProductEntity> findAllByCategory(Category category);
}
