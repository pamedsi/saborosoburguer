package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.burger.Burger;

@Entity
@Table
@NoArgsConstructor
public class BurgerSale extends SoldItem{
    @ManyToOne
    @JoinColumn(name = "burger_id")
    private Burger soldburger;

    public BurgerSale(CustomerOrder orderThatSold, Burger soldburger, Long quantity) {
        this.soldburger = soldburger;
        setOrderThatSold(orderThatSold);
        setQuantity(quantity);
        setSingleUnitySoldFor(soldburger.getPrice());
    }
}