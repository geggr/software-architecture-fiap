package br.com.fiap.totem_express.presentation.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.totem_express.application.product.CreateProductUseCase;
import br.com.fiap.totem_express.application.product.DeleteProductUseCase;
import br.com.fiap.totem_express.application.product.output.NewProductView;
import br.com.fiap.totem_express.presentation.product.request.CreateProductRequest;
import jakarta.transaction.Transactional;

@RestController
public class ProductController implements ProductDocumentation {

    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, DeleteProductUseCase deleteProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
    }

    @Override
    @Transactional
    @PostMapping("/api/product/create")
    public ResponseEntity<NewProductView> create(CreateProductRequest request) {
        NewProductView createProduct = createProductUseCase.create(request);
        return ResponseEntity.ok(createProduct);
    }

    @Override
    @Transactional
    @DeleteMapping("/api/product/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteProductUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

}
