package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Category {

    public Category(String name, Category parent) {
        this.parent = parent;
        this.title = name;
    }

    public Category(String name) {
        this.title = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JoinColumn(name = "parent_id", foreignKey = @ForeignKey(name = "parent_fk_id"))
    @ManyToOne(fetch = FetchType.LAZY)
    private Category parent;
}
