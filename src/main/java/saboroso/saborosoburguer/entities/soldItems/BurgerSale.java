package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import saboroso.saborosoburguer.entities.burger.Burger;
import saboroso.saborosoburguer.entities.CustomerOrder;

import java.math.BigDecimal;

@Entity
@Table
@Data
@NoArgsConstructor
public class BurgerSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "client_order_id")
    private CustomerOrder orderThatSold;
    @ManyToOne
    @JoinColumn(name = "burger_id")
    private Burger soldburger;
    @Column
    private Long quantity;
    @Column (updatable = false)
    private BigDecimal singleUnitySoldFor;
    public BurgerSale(CustomerOrder orderThatSold, Burger soldburger, Long quantity) {
        this.orderThatSold = orderThatSold;
        this.soldburger = soldburger;
        this.quantity = quantity;
        this.singleUnitySoldFor = soldburger.getPrice();
    }
}