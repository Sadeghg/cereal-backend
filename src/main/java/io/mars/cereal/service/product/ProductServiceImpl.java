package io.mars.cereal.service.product;

import io.mars.cereal.data.product.ProductRepository;
import io.mars.cereal.model.Product;
import io.mars.cereal.model.exception.ContentNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public Iterable<Product> saveAll(Iterable<Product> products) {
        return repository.saveAll(products);
    }

    @Override
    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ContentNotFound("no such product found"));
    }

    @Override
    public Iterable<Product> findAllById(Iterable<Long> ids) {
        return repository.findAllById(ids);
    }

    @Override
    public void deleteAllById(Iterable<Long> ids) {
        repository.deleteAllById(ids);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
