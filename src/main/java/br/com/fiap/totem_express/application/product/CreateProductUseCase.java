package br.com.fiap.totem_express.application.product;

import br.com.fiap.totem_express.application.product.input.NewProductInput;
import br.com.fiap.totem_express.application.product.output.NewProductView;

public interface CreateProductUseCase {
    NewProductView create(NewProductInput input);
}
