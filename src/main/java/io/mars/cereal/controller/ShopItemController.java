package io.mars.cereal.controller;

import io.mars.cereal.model.ShopItem;
import io.mars.cereal.service.shopitem.ShopItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shopItem/")
public class ShopItemController {

    private final ShopItemService service;

    @GetMapping("{id}")
    public ResponseEntity<ShopItem> find(@PathVariable Long id) {
        return ResponseEntity.ok(service.find(id));
    }

    @GetMapping("shop/{shopId}")
    public ResponseEntity<List<ShopItem>> findInShopWithCategories(@PathVariable Long shopId,
                                                                   @RequestParam(required = false) List<String> categories) {
        return ResponseEntity.ok(service.findInShopWithCategories(shopId, categories));
    }

    @GetMapping("shop/{shopId}/jpql")
    public ResponseEntity<List<ShopItem>> findInShopWithProductJPQL(@PathVariable Long shopId,
                                                                    @RequestParam Long productId) {
        return ResponseEntity.ok(service.findInShopWithProductJPQL(shopId, productId));
    }

    @GetMapping("shop/{shopId}/spec")
    public ResponseEntity<List<ShopItem>> findInShopWithProductSPEC(@PathVariable Long shopId,
                                                                    @RequestParam Long productId) {
        return ResponseEntity.ok(service.findInShopWithProductSPEC(shopId, productId));
    }

    @GetMapping("{shopId}/{productId}")
    public ResponseEntity<ShopItem> findInShopByProduct(@PathVariable Long shopId,
                                                        @PathVariable Long productId) {
        return ResponseEntity.ok(service.findInShop(shopId, productId));
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
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
