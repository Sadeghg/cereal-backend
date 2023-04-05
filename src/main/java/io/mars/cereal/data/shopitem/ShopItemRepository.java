package io.mars.cereal.data.shopitem;

import io.mars.cereal.model.ShopItem;
import org.springframework.data.repository.CrudRepository;

public interface ShopItemRepository extends CrudRepository<ShopItem, Long> {
}
