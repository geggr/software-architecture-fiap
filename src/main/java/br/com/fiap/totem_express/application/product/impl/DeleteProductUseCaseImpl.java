package br.com.fiap.totem_express.application.product.impl;

import br.com.fiap.totem_express.application.product.DeleteProductUseCase;
import br.com.fiap.totem_express.application.product.ProductGateway;

public class DeleteProductUseCaseImpl implements DeleteProductUseCase {
    
    private final ProductGateway productGateway;

    public DeleteProductUseCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public void delete(Long id) {
        productGateway.delete(id);
    }
    
}
