package io.mars.cereal.service.category;

import io.mars.cereal.data.category.CategoryRepository;
import io.mars.cereal.model.Category;
import io.mars.cereal.model.exception.ContentNotFound;
import io.mars.cereal.service.generic.GenericService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Iterable<Category> saveAll(Iterable<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new ContentNotFound("no such category found"));
    }

    @Override
    public Iterable<Category> findAllById(Iterable<Long> ids) {
        return categoryRepository.findAllById(ids);
    }

    @Override
    public void deleteAllById(Iterable<Long> ids) {
        categoryRepository.deleteAllById(ids);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
