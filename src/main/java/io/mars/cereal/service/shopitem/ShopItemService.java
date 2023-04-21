package io.mars.cereal.service.shopitem;

import io.mars.cereal.model.ShopItem;
import io.mars.cereal.service.generic.GenericService;

import java.util.List;

public interface ShopItemService extends GenericService<ShopItem, Long> {

    ShopItem findInShop(Long shopId, Long product);

    List<ShopItem> findInShopWithCategories(Long shop, List<String> categories);

    List<ShopItem> findInShopWithProductJPQL(Long shop, Long product);

    List<ShopItem> findInShopWithProductSPEC(Long shop, Long product);

}
