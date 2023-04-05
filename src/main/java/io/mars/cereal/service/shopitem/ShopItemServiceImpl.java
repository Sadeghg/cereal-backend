package io.mars.cereal.service.shopitem;

import io.mars.cereal.data.shopitem.ShopItemRepository;
import io.mars.cereal.model.ShopItem;
import io.mars.cereal.model.exception.ContentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopItemServiceImpl implements ShopItemService{

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
}
