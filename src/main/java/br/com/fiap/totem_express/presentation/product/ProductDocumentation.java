package br.com.fiap.totem_express.presentation.product;

import org.springframework.http.ResponseEntity;

import br.com.fiap.totem_express.application.product.output.NewProductView;
import br.com.fiap.totem_express.application.product.output.UpdateProductView;
import br.com.fiap.totem_express.presentation.product.request.CreateProductRequest;
import br.com.fiap.totem_express.presentation.product.request.UpdateProductRequest;

public interface ProductDocumentation {
    ResponseEntity<NewProductView> create(CreateProductRequest request);
    ResponseEntity<Void> delete(Long id);
    ResponseEntity<UpdateProductView> update(UpdateProductRequest request);
}

