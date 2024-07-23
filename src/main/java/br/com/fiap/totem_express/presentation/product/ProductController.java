package br.com.fiap.totem_express.presentation.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.totem_express.application.product.CreateProductUseCase;
import br.com.fiap.totem_express.application.product.output.NewProductView;
import br.com.fiap.totem_express.presentation.product.request.CreateProductRequest;
import jakarta.transaction.Transactional;

@RestController
public class ProductController implements ProductDocumentation{
    
    private final CreateProductUseCase createProductUseCase;
    
    public ProductController(CreateProductUseCase createProductUseCase) {
        this.createProductUseCase = createProductUseCase;
    }

    @Override
    @Transactional
    @PostMapping("/api/product/create")
    public ResponseEntity<NewProductView> create(CreateProductRequest request) {
        NewProductView createProduct = createProductUseCase.create(request);;
        return ResponseEntity.ok(createProduct);
    }
    
}
