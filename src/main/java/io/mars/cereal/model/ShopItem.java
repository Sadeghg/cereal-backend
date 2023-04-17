package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class ShopItem {

    public ShopItem(Shop shop, Product product, Long quantity, Double price,
                    List<ProductPrice> prices, List<Category> categories){
        this.categories = categories;
        this.quantity = quantity;
        this.product = product;
        this.prices = prices;
        this.price = price;
        this.shop = shop;
    }

    @Id
    @SequenceGenerator(name = "cuteSeq", sequenceName = "cute_seq"
            , initialValue = 1, allocationSize = 27)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuteSeq")
    private Long id;

    @JoinColumn(name = "shop_id", foreignKey = @ForeignKey(name = "shop_fk_id"), nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Shop shop;

    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "product_shop_item_fk_id"), nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;

    @Column(name = "product_price", nullable = false)
    private Double price;

    @Column(name = "product_quantity", nullable = false)
    private Long quantity;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "prices_for_shop_item",
            joinColumns = {@JoinColumn(
                    name = "shop_item_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "shop_item_price_fk_id"))},
            inverseJoinColumns = {@JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "price_of_shop_item_fk_id")
            )})
    private List<ProductPrice> prices;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "categories_for_shop_item",
            joinColumns = {@JoinColumn(
                    name = "shop_item_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "shop_item_category_fk_id"))},
            inverseJoinColumns = {@JoinColumn(
                    name = "category_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "category_of_shop_item_fk_id")
            )})
    private List<Category> categories;
}
