package io.mars.cereal.controller;

import io.mars.cereal.model.Product;
import io.mars.cereal.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product/")
public class ProductController {

    private final ProductService service;

    @GetMapping("{id}")
    public ResponseEntity<Product> find(@PathVariable Long id) {
        return ResponseEntity.ok(service.find(id));
    }

    @GetMapping
    public ResponseEntity<List<Product>> find(@RequestParam String categoryTitle) {
        return ResponseEntity.ok(service.findByCategory(categoryTitle));
    }

    @PostMapping("save")
    public ResponseEntity<Product> save(@RequestBody Product product) {
        return ResponseEntity.ok(service.save(product));
    }

    @PutMapping("update")
    public ResponseEntity<Product> update(@RequestBody Product product) {
        return ResponseEntity.ok(service.save(product));
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable  Long id) {
        service.delete(id);
    }
}
