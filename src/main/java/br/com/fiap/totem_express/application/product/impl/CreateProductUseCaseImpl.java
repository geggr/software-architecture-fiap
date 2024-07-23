package br.com.fiap.totem_express.application.product.impl;

import br.com.fiap.totem_express.application.product.CreateProductUseCase;
import br.com.fiap.totem_express.application.product.ProductGateway;
import br.com.fiap.totem_express.application.product.input.NewProductInput;
import br.com.fiap.totem_express.application.product.output.NewProductView;
import br.com.fiap.totem_express.domain.product.Product;

public class CreateProductUseCaseImpl implements CreateProductUseCase{

    private final ProductGateway productGateway;
    
    public CreateProductUseCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public NewProductView create(NewProductInput input) {
        Product domain = input.toDomain();
        Product createdProduct = productGateway.save(domain);

        return new NewProductView.CreatedView(
                createdProduct.getId(),
                createdProduct.getName(),
                createdProduct.getDescription(),
                createdProduct.getImagePath(),
                createdProduct.getPrice(),
                createdProduct.getCategory()
        );
    }
    
}
