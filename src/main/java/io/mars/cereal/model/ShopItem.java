package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Fetch;

@Data
@Entity
public class ShopItem {

    @Id
    @SequenceGenerator(name = "cuteSequence", sequenceName = "cuteSequence"
            , initialValue = 1, allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuteSequence")
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
