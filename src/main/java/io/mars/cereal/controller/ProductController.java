package io.mars.cereal.controller;

import io.mars.cereal.model.Product;
import io.mars.cereal.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product/")
public class ProductController {

    private final ProductService service;

    @GetMapping("{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return null;
    }

    @PostMapping("save")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(service.save(product));
    }

    @PutMapping("update")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return null;
    }

    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable  Long id) {

    }
}
