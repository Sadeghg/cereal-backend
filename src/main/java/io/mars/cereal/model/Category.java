package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Category {

    public Category(String name, Set<Category> children) {
        this.children = children;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    @Id
    @SequenceGenerator(name = "cuteSeq", sequenceName = "cute_seq"
            , initialValue = 1, allocationSize = 27)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuteSeq")
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categories_prent_child",
            joinColumns = {@JoinColumn(
                    name = "child_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "parent_category_fk_id"))},
            inverseJoinColumns = {@JoinColumn(
                    name = "parent_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "child_of_shop_fk_id")
            )})
    private Set<Category> children;
}
