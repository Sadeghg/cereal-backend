package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CardItem {

    public static CardItem cardItem(){
        return new CardItem();
    }

    @Id
    @SequenceGenerator(name="cuteSequence", sequenceName = "cuteSequence"
            , initialValue = 1, allocationSize = 50)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuteSequence")
    private Long id;

    @Column(name = "item_quantity", nullable = false)
    private Integer quantity;

    @Column(name = "item_price", nullable = false)
    private Double price;

    @JoinColumn(name = "card_id", foreignKey = @ForeignKey(name = "card_fk_id"))
    @ManyToOne(fetch = FetchType.EAGER)
    private Card card;

    @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "product_fk_id"))
    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;


}
