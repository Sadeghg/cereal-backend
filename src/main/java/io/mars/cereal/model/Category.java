package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
}
