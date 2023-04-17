package io.mars.cereal.data.product;

import io.mars.cereal.model.Category;
import io.mars.cereal.model.Product;
import jakarta.persistence.criteria.Join;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
public class ProductSpecification {

    public static Specification<Product> categoryTitle(String title) {
        return ((root, query, cb) -> {
            Join<Category, Product> join = root.join("categories");
            return cb.equal(join.get("title"), title);
        });
    }
}
