package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;

@Data
@Entity
@NoArgsConstructor
public class ShopItem {

    public ShopItem(Shop shop, Product product, Double price, Long quantity){
        this.quantity = quantity;
        this.product = product;
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
}
