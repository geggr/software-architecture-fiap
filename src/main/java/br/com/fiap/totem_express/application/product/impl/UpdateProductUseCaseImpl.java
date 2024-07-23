package br.com.fiap.totem_express.application.product.impl;

import java.util.Optional;

import br.com.fiap.totem_express.application.product.ProductGateway;
import br.com.fiap.totem_express.application.product.UpdateProductUseCase;
import br.com.fiap.totem_express.application.product.input.UpdateProductInput;
import br.com.fiap.totem_express.application.product.output.UpdateProductView;
import br.com.fiap.totem_express.application.product.output.UpdateProductView.UpdatedView;
import br.com.fiap.totem_express.domain.product.Product;
import br.com.fiap.totem_express.infrastructure.product.ProductRepository;

public class UpdateProductUseCaseImpl implements UpdateProductUseCase {
    private final ProductGateway productGateway;

    public UpdateProductUseCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public Optional<UpdateProductView> update(UpdateProductInput input) {
        Optional<Product> possibleProduct = productGateway.findById(input.id());
        if (possibleProduct.isEmpty()) {
            return Optional.empty();
        }
        Product product = possibleProduct.get();
        product.update(input);
        Product save = productGateway.save(product);
        return Optional.of(new UpdateProductView.UpdatedView(
                save.getId(),
                save.getName(),
                save.getDescription(),
                save.getImagePath(),
                save.getPrice(),
                save.getCategory()));
    }

}
