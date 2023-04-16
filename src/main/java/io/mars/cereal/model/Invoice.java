package io.mars.cereal.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Invoice {

    public Invoice(Double totalPrice, List<InvoiceItem> invoiceItemList){
        this.invoiceItemList = invoiceItemList;
        this.totalPrice= totalPrice;
    }

    @Id
    @SequenceGenerator(name = "cuteSeq", sequenceName = "cute_seq"
            , initialValue = 1, allocationSize = 27)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cuteSeq")
    private Long id;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "invoice_item_list",
            uniqueConstraints = @UniqueConstraint(name = "invoice_item_key", columnNames = {"invoice_item_id"}),
            joinColumns = {@JoinColumn(
                    name = "invoice_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "invoice_fk_id"))},
            inverseJoinColumns = {@JoinColumn(
                    name = "invoice_item_id",
                    referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "invoice_item_fk_id")
            )})
    private List<InvoiceItem> invoiceItemList;

}
