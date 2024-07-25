package br.com.fiap.totem_express.presentation.product;

import java.util.List;

import br.com.fiap.totem_express.application.product.output.ProductView;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;

import br.com.fiap.totem_express.presentation.product.request.CreateProductRequest;
import br.com.fiap.totem_express.presentation.product.request.UpdateProductRequest;

public interface ProductDocumentation {

    @Operation(summary = "Create a new product", description = "Creates a new product with the given details")
    ResponseEntity<ProductView> create(CreateProductRequest request);

    @Operation(summary = "Delete a product", description = "Deletes the product with the given ID")
    ResponseEntity<Void> delete(Long id);

    @Operation(summary = "Update a product", description = "Updates the product with the given details")
    ResponseEntity<ProductView> update(UpdateProductRequest request);

    @Operation(summary = "Find products by category", description = "Returns all products according to category")
    ResponseEntity<List<ProductView>> find(String category);
}

