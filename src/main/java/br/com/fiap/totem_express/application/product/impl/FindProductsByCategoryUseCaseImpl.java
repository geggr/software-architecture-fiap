package br.com.fiap.totem_express.application.product.impl;

import java.util.List;
import static java.util.stream.Collectors.toList;

import br.com.fiap.totem_express.application.product.FindProductsByCategoryUseCase;
import br.com.fiap.totem_express.application.product.ProductGateway;
import br.com.fiap.totem_express.application.product.output.NewProductView;
import br.com.fiap.totem_express.domain.product.Category;
import br.com.fiap.totem_express.domain.product.Product;

public class FindProductsByCategoryUseCaseImpl implements FindProductsByCategoryUseCase {

    private final ProductGateway productGateway;

    public FindProductsByCategoryUseCaseImpl(ProductGateway productGateway) {
        this.productGateway = productGateway;
    }

    @Override
    public List<NewProductView> findAllByCategory(Category category) {        
        List<Product> products = productGateway.findAllByCategory(category);

        return products.stream().map(product -> new NewProductView.CreatedView(
                        product.getId(),
                        product.getName(),
                        product.getDescription(),
                        product.getImagePath(),
                        product.getPrice(),
                        product.getCategory())
                ).collect(toList());
    }

}
