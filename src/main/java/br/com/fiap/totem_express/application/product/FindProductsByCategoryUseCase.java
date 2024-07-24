package br.com.fiap.totem_express.application.product;

import java.util.List;

import br.com.fiap.totem_express.application.product.output.NewProductView;
import br.com.fiap.totem_express.domain.product.Category;

public interface FindProductsByCategoryUseCase {
    List<NewProductView> findAllByCategory(Category category);
}
