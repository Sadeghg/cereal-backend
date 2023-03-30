package io.mars.cereal.data.product;


import io.mars.cereal.model.Product;

import java.util.Optional;

public interface ProductRepository {

    Optional<Product> save(Product product);

    Optional<Product> findById(Long id);

    void deleteById(Long id);
}
