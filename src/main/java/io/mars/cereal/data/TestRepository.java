package io.mars.cereal.data;

import io.mars.cereal.model.Category;
import io.mars.cereal.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestRepository {


    @PersistenceContext
    private EntityManager entityManager;

    public void critetira(){

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // This is the query on the main object which it is Product class
        CriteriaQuery<Product> mainBookQuery = criteriaBuilder.createQuery(Product.class);

        // Define the root entity (Product)
        Root<Product> root = mainBookQuery.from(Product.class);

        // Join with category table
        Join<Product, Category> categoryJoin = root.join("categories");


        Predicate subqueryPredicate = criteriaBuilder
                .equal(categoryJoin.get("title"), "subcategory-level-two-21019");

        mainBookQuery
                .select(root)
                .where(subqueryPredicate);
        List<Product> productList = entityManager.createQuery(mainBookQuery).getResultList();
        System.out.println("mee");
    }
}
