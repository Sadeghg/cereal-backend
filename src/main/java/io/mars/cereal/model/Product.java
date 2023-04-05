package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    public Product(String name, Company company, List<Detail> details){
        this.details = details;
        this.company = company;
        this.name = name;
    }

    @Id
    @SequenceGenerator(name = "cuteSequence", sequenceName = "cuteSequence"
            , initialValue = 1, allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "product_name", columnDefinition = "TEXT")
    private String name;

    @JoinColumn(name = "company_id", foreignKey = @ForeignKey(name = "company_fk_id"))
    @ManyToOne(fetch = FetchType.EAGER)
    private Company company;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_details",
            joinColumns = {@JoinColumn(
                    name = "product_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "product_details_fk_id"))},
            inverseJoinColumns = {@JoinColumn(
                    name = "detail_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "detail_fk_id")
            )})
    private List<Detail> details;
}
