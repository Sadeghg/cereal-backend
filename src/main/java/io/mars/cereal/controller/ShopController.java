package io.mars.cereal.controller;

import io.mars.cereal.model.Shop;
import io.mars.cereal.service.shop.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop/")
public class ShopController {

    private final ShopService service;

    @GetMapping("{id}")
    public ResponseEntity<Shop> find(@PathVariable Long id) {
        return ResponseEntity.ok(service.find(id));
    }

    @PostMapping("save")
    public ResponseEntity<Shop> save(@RequestBody Shop shop) {
        return ResponseEntity.ok(service.save(shop));
    }

    @PutMapping("update")
    public ResponseEntity<Shop> update(@RequestBody Shop shop) {
        return ResponseEntity.ok(service.save(shop));
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
