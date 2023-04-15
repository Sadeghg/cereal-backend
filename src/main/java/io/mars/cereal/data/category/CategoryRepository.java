package io.mars.cereal.data.category;

import io.mars.cereal.model.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT category FROM Category category WHERE category.name LIKE %:start%")
    Set<Category> findByNameLike(String  start);

    @Query(value = "SELECT * FROM category c WHERE c.id NOT IN " +
            "(SELECT DISTINCT child_id FROM categories_prent_child)", nativeQuery = true)
    List<Category> findMainCategories();

    @Query(value = "SELECT * FROM category c WHERE c.id IN " +
            "(SELECT DISTINCT child_id FROM categories_prent_child)", nativeQuery = true)
    List<Category> findSubCategories();
}
