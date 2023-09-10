package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

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
