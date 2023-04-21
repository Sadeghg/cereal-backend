package io.mars.cereal.data.shopitem;

import io.mars.cereal.model.ShopItem;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopItemRepository extends CrudRepository<ShopItem, Long>, JpaSpecificationExecutor<ShopItem> {

    @Query(value = "SELECT si FROM ShopItem si WHERE si.shop.id=:shopId AND si.product.id=:productId")
    List<ShopItem> findByShopAndProduct(Long shopId, Long productId);

}
