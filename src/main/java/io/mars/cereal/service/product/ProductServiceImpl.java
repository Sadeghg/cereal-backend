package io.mars.cereal.service.product;

import io.mars.cereal.data.product.ProductRepository;
import io.mars.cereal.model.Product;
import io.mars.cereal.model.exception.ContentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.mars.cereal.data.product.ProductSpecification.categoryTitle;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public Product save(Product company) {
        return repository.save(company);
    }

    @Override
    public Iterable<Product> saveAll(Iterable<Product> companies) {
        return repository.saveAll(companies);
    }

    @Override
    public Product find(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ContentNotFoundException("no such product found"));
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

    @Override
    public List<Product> findByCategory(String categoryTitle) {
        return repository.findAll(categoryTitle(categoryTitle));
    }
}
