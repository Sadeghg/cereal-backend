package io.mars.cereal.service.shopitem;

import io.mars.cereal.data.GenericSpecification;
import io.mars.cereal.data.shopitem.ShopItemRepository;
import io.mars.cereal.model.ShopItem;
import io.mars.cereal.model.exception.ContentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.mars.cereal.data.shopitem.ShopItemSpecification.*;

@Service
@RequiredArgsConstructor
public class ShopItemServiceImpl implements ShopItemService {

    private final ShopItemRepository shopItemRepository;

    @Override
    public ShopItem save(ShopItem shopItem) {
        return shopItemRepository.save(shopItem);
    }

    @Override
    public Iterable<ShopItem> saveAll(Iterable<ShopItem> shopItems) {
        return shopItemRepository.saveAll(shopItems);
    }

    @Override
    public ShopItem find(Long id) {
        return shopItemRepository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("no such shop item"));
    }

    @Override
    public Iterable<ShopItem> findAllById(Iterable<Long> ids) {
        return shopItemRepository.findAllById(ids);
    }

    @Override
    public void deleteAllById(Iterable<Long> ids) {
        shopItemRepository.deleteAllById(ids);
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public ShopItem findInShop(Long shopId, Long product) {
        return shopItemRepository.findOne(byShop(shopId).and(byProduct(product)))
                .orElseThrow(() -> new ContentNotFoundException("no such product exception"));
    }

    @Override
    public List<ShopItem> findInShopWithCategories(Long shopId, List<String> categories) {
        return shopItemRepository.findAll(categories == null ? byShop(shopId)
                : byShop(shopId).and(byCategories(categories)));
    }

    @Override
    public List<ShopItem> findInShopWithProductJPQL(Long shop, Long product) {
        return shopItemRepository.findByShopAndProduct(shop, product);
    }

    @Override
    public List<ShopItem> findInShopWithProductSPEC(Long shop, Long product) {
        return shopItemRepository.findAll(and(byShop(shop), byProduct(product)));
    }
}
