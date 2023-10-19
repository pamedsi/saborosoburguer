package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.menuItems.Drink;

@Entity
@Table
@NoArgsConstructor
public class DrinkSale extends SoldItem{
    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink soldDrink;

    public DrinkSale(CustomerOrder orderThatSold, Drink soldDrink, int quantitty) {
        this.soldDrink = soldDrink;
        setOrderThatSold(orderThatSold);
        setQuantity(quantitty);
        setSingleUnitySoldFor(soldDrink.getPrice());
    }
}