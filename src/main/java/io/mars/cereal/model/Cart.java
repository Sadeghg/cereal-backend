package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Cart {

    public Cart(Double totalPrice, LocalDateTime purchaseDate){
        this.purchaseDate = purchaseDate;
        this.totalPrice = totalPrice;
    }

    public Cart(Double totalPrice){
        this.totalPrice = totalPrice;
    }

    @Id
    @SequenceGenerator(name = "cuteSeq", sequenceName = "cute_seq"
            , initialValue = 1, allocationSize = 27)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuteSeq")
    private Long id;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "purchase_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime purchaseDate;
}
