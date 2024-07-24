package br.com.fiap.totem_express.presentation.product;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.totem_express.application.product.CreateProductUseCase;
import br.com.fiap.totem_express.application.product.DeleteProductUseCase;
import br.com.fiap.totem_express.application.product.FindProductsByCategoryUseCase;
import br.com.fiap.totem_express.application.product.UpdateProductUseCase;
import br.com.fiap.totem_express.application.product.output.NewProductView;
import br.com.fiap.totem_express.application.product.output.UpdateProductView;
import br.com.fiap.totem_express.domain.product.Category;
import br.com.fiap.totem_express.presentation.product.request.CreateProductRequest;
import br.com.fiap.totem_express.presentation.product.request.UpdateProductRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
public class ProductController implements ProductDocumentation {

    private final CreateProductUseCase createProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final FindProductsByCategoryUseCase findProductsByCategoryUseCase;

    public ProductController(CreateProductUseCase createProductUseCase, DeleteProductUseCase deleteProductUseCase,
     UpdateProductUseCase updateProductUseCase, FindProductsByCategoryUseCase findProductsByCategoryUseCase) {
        this.createProductUseCase = createProductUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.updateProductUseCase = updateProductUseCase;
        this.findProductsByCategoryUseCase = findProductsByCategoryUseCase;
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

    @Override
    @Transactional
    @GetMapping("/api/product/{category}")
    public ResponseEntity<List<NewProductView>> findAllByCategory(@PathVariable String category) {
        Optional<Category> possibleCategory = Category.findByName(category);
        if (possibleCategory.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Category categoryName = possibleCategory.get();
        List<NewProductView> products = findProductsByCategoryUseCase.findAllByCategory(categoryName);
        if (products.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

}
