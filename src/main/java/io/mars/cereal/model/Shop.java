package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Shop {

    public Shop(String name, String description,
                List<Category> categories){
        this.description = description;
        this.categories = categories;
        this.name = name;
    }
    public Shop(String name, String description){
        this.description = description;
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categories_for_shop",
            joinColumns = {@JoinColumn(
                    name = "shop_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "shop_category_fk_id"))},
            inverseJoinColumns = {@JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "category_of_shop_fk_id")
            )})
    private List<Category> categories;
}
