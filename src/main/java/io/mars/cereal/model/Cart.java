package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Cart {

    public Cart(Double totalPrice, LocalDateTime purchaseDate, LocalDateTime addDate){
        this.purchaseDate = purchaseDate;
        this.addDate = addDate;
        this.totalPrice = totalPrice;
    }

    public Cart(Double totalPrice){
        this.totalPrice = totalPrice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "purchase_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime purchaseDate;

    @Column(name = "add_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime addDate;
}
