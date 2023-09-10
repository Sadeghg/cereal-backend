package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class InvoiceItem {

    public InvoiceItem(CartItem cartItem){
        ShopItem shopItem = cartItem.getItem();
        this.quantity = cartItem.getQuantity();
        this.price = shopItem.getPrice();
        this.item = shopItem;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "shop_item_invoice_fk_id"))
    @OneToOne(fetch = FetchType.EAGER)
    private ShopItem item;

    @Column(name = "product_price", nullable = false)
    private Double price;

    @Column(name = "product_quantity", nullable = false)
    private Integer quantity;
}
