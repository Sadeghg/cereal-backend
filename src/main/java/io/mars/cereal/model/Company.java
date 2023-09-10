package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    public Company(String name, List<Category> categories) {
        this.categories = categories;
        this.name = name;
    }

    public Company(Long id, String name) {
        this.name = name;
        this.id = id;
    }

    public Company(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", columnDefinition = "TEXT")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categories_for_company",
            joinColumns = {@JoinColumn(
                    name = "company_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "company_category_fk_id"))},
            inverseJoinColumns = {@JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "category_of_company_fk_id")
            )})
    private List<Category> categories;

}
