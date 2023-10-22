package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.menuItems.Drink;

import java.math.BigDecimal;

@Entity
@Table
@NoArgsConstructor
@Getter
public class DrinkSale extends MenuItemSale {
    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink soldDrink;

    @Column
    private int quantity;

    @Column (updatable = false)
    private BigDecimal singleUnitySoldFor;

    public DrinkSale(CustomerOrder orderThatSold, Drink soldDrink, int quantity) {
        this.soldDrink = soldDrink;
        setOrderThatSold(orderThatSold);
        this.quantity = quantity;
        this.singleUnitySoldFor = soldDrink.getPrice();
    }
}