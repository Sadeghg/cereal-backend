package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class ProductPrice {

    public ProductPrice(Double price, LocalDateTime priceDate){
        this.priceDate = priceDate;
        this.price = price;
    }

    @Id
    @SequenceGenerator(name = "cuteSeq", sequenceName = "cute_seq"
            , initialValue = 1, allocationSize = 27)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuteSeq")
    private Long id;

    @Column(name = "product_price", nullable = false)
    private Double price;

    @Column(name = "price_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime priceDate;
}
