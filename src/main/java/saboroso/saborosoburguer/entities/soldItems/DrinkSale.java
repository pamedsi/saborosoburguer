package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.menuItems.Drink;

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

    public DrinkSale(CustomerOrder orderThatSold, Drink soldDrink, int quantity) {
        this.soldDrink = soldDrink;
        setOrderThatSold(orderThatSold);
        this.quantity = quantity;
        setSingleUnitySoldFor(soldDrink.getPrice());
    }
}