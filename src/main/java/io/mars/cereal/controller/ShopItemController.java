package io.mars.cereal.controller;

import io.mars.cereal.model.ShopItem;
import io.mars.cereal.service.shopitem.ShopItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shopItem/")
public class ShopItemController {

    private final ShopItemService service;

    @GetMapping("{id}")
    public ResponseEntity<ShopItem> find(@PathVariable Long id) {
        return ResponseEntity.ok(service.find(id));
    }

    @PostMapping("save")
    public ResponseEntity<ShopItem> save(@RequestBody ShopItem shopItem) {
        return ResponseEntity.ok(service.save(shopItem));
    }

    @PutMapping("update")
    public ResponseEntity<ShopItem> update(@RequestBody ShopItem shopItem) {
        return ResponseEntity.ok(service.save(shopItem));
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
