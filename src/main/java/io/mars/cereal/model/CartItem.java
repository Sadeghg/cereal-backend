package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class CartItem {

    public CartItem(Integer quantity, Cart cart, ShopItem item){
        this.quantity = quantity;
        this.cart = cart;
        this.item = item;
    }

    @Id
    @SequenceGenerator(name = "cuteSeq", sequenceName = "cute_seq"
            , initialValue = 1, allocationSize = 27)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuteSeq")
    private Long id;

    @Column(name = "item_quantity", nullable = false)
    private Integer quantity;

    @JoinColumn(name = "cart_id", foreignKey = @ForeignKey(name = "cart_fk_id"))
    @ManyToOne(fetch = FetchType.EAGER)
    private Cart cart;

    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "product_card_item_fk_id"))
    @ManyToOne(fetch = FetchType.EAGER)
    private ShopItem item;
}
