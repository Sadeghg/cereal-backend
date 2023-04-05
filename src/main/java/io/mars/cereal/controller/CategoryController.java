package io.mars.cereal.controller;

import io.mars.cereal.model.Category;
import io.mars.cereal.service.category.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category/")
public class CategoryController {

    private CategoryServiceImpl service;

    @GetMapping("{id}")
    public ResponseEntity<Category> find(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Category> save(@RequestBody Category category){
        return ResponseEntity.ok(service.save(category));
    }

    @PutMapping
    public ResponseEntity<Category> update(@RequestBody Category category){
        return ResponseEntity.ok(service.save(category));
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
