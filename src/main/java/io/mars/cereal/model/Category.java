package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Category {

    @Id
    private Long id;

    private String name;

    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "parent_of_category_fk_id"))
    @ManyToOne()
    private Category parent;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "category_children",
            joinColumns = {@JoinColumn(
                    name = "parent_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "parent_category_parent_fk"))},
            inverseJoinColumns = {@JoinColumn(
                    name = "child_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "child_category_fk_id")
            )})
    private Set<Category> children;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "products_in_category",
            joinColumns = {@JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "category_of_product_fk_id"))},
            inverseJoinColumns = {@JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "product_category_fk_id")
            )})
    private Set<Product> products;
}
