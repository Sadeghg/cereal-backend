package io.mars.cereal.model;

import io.mars.cereal.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

@Data
@Entity(name = "purchase_order")
@NoArgsConstructor
public class Order {

    public Order(Cart cart){
        this.status = OrderStatus.INITIALIZED;
        this.cart = cart;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "cart_id", foreignKey = @ForeignKey(name = "order_cart_fk_id"))
    @OneToOne(fetch = FetchType.EAGER)
    private Cart cart;

    @Enumerated(value = EnumType.STRING)
    private OrderStatus status;
}
