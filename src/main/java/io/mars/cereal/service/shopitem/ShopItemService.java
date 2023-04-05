package io.mars.cereal.service.shopitem;

import io.mars.cereal.model.ShopItem;
import io.mars.cereal.service.generic.GenericService;
import org.springframework.data.repository.CrudRepository;

public interface ShopItemService extends GenericService<ShopItem, Long> {
}
