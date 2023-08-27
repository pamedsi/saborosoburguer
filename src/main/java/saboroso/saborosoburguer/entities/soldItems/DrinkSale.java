package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.Drink;

import java.math.BigDecimal;

@Entity
@Table
@NoArgsConstructor
@Data
public class DrinkSale {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_order_id")
    private CustomerOrder orderThatSold;
    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink soldDrink;
    @Column
    private Long amount;
    @Column (updatable = false)
    private BigDecimal singleUnitySoldFor;
    public DrinkSale(CustomerOrder orderThatSold, Drink soldDrink, Long amount) {
        this.orderThatSold = orderThatSold;
        this.soldDrink = soldDrink;
        this.amount = amount;
        this.singleUnitySoldFor = soldDrink.getPrice();
    }
}