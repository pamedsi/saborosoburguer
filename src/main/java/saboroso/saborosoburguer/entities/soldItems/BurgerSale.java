package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.menuItems.burger.Burger;
import saboroso.saborosoburguer.entities.menuItems.burger.BurgerBread;

@Entity
@Table
@NoArgsConstructor
public class BurgerSale extends SoldItem{
    @ManyToOne
    @JoinColumn(name = "burger_id")
    private Burger soldburger;
    @ManyToOne
    @JoinColumn (name = "bread_id")
    private BurgerBread bread;

    public BurgerSale(CustomerOrder orderThatSold, Burger soldburger, BurgerBread bread) {
        this.soldburger = soldburger;
        this.bread = bread;
        setOrderThatSold(orderThatSold);
        setSingleUnitySoldFor(soldburger.getPrice());
    }
}