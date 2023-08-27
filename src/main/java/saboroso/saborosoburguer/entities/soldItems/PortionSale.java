package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import lombok.Setter;
import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.Portion;

import java.math.BigDecimal;

@Entity
@Table
@NoArgsConstructor
public class PortionSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_order_id")
    private CustomerOrder orderThatSold;
    @ManyToOne
    @JoinColumn(name = "portion_id")
    private Portion soltPortion;
    @Column
    private Long amount;
    @Column (updatable = false)
    private BigDecimal singleUnitySoldFor;
    public PortionSale(CustomerOrder orderThatSold, Portion soltPortion, Long amount) {
        this.orderThatSold = orderThatSold;
        this.soltPortion = soltPortion;
        this.amount = amount;
        this.singleUnitySoldFor = soltPortion.getPrice();
    }
}
