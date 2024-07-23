package br.com.fiap.totem_express.presentation.product;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.totem_express.application.product.CreateProductUseCase;
import br.com.fiap.totem_express.application.product.DeleteProductUseCase;
import br.com.fiap.totem_express.application.product.UpdateProductUseCase;
import br.com.fiap.totem_express.application.product.output.NewProductView;
import br.com.fiap.totem_express.application.product.output.UpdateProductView;
import br.com.fiap.totem_express.presentation.product.request.CreateProductRequest;
import br.com.fiap.totem_express.presentation.product.request.UpdateProductRequest;
import jakarta.transaction.Transactional;

@RestController
public class ProductController implements ProductDocumentation {

    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, DeleteProductUseCase deleteProductUseCase, UpdateProductUseCase updateProductUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
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

    @Override
    @Transactional
    @PutMapping("/api/product/update")
    public ResponseEntity<UpdateProductView> update(UpdateProductRequest request) {
        Optional<UpdateProductView> possibleUpdateProduct = updateProductUseCase.update(request);
        return possibleUpdateProduct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
