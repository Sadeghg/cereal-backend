package io.mars.cereal.data.product;


import io.mars.cereal.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

}
