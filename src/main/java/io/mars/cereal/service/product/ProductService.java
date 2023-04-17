package io.mars.cereal.service.product;

import io.mars.cereal.model.Product;
import io.mars.cereal.service.generic.GenericService;

import java.util.List;

public interface ProductService extends GenericService<Product, Long> {

    List<Product> findByCategory(String categoryTitle);
}
