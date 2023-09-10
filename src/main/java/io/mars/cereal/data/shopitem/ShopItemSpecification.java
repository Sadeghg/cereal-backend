package io.mars.cereal.data.shopitem;
import io.mars.cereal.data.GenericSpecification;
import io.mars.cereal.model.ShopItem;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class ShopItemSpecification {

    public static Specification<ShopItem> and(Specification<ShopItem>... specs){
        return GenericSpecification.and(specs);
    }

    public static Specification<ShopItem> or(Specification<ShopItem>... specs){
        return GenericSpecification.or(specs);
    }
    public static Specification<ShopItem> byCategories(List<String> categories) {
        return ((root, query, cb) -> root.join("categories").get("title").in(categories));
    }

    public static Specification<ShopItem> byCategory(String title) {
        return ((root, query, cb) -> cb.equal(root.join("categories").get("title"), title));
    }

    public static Specification<ShopItem> byPrice(String title) {
        return ((root, query, cb) -> cb.equal(root.join("prices").get("title"), title));
    }

    public static Specification<ShopItem> byProduct(Long product) {
        return ((root, query, cb) -> cb.equal(root.join("product").get("id"), product));
    }

    public static Specification<ShopItem> byShop(Long shopId) {
        return ((root, query, cb) -> cb.equal(root.join("shop").get("id"), shopId));
    }
}

