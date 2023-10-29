package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.menuItems.burger.Burger;
import saboroso.saborosoburguer.entities.menuItems.burger.BurgerBread;
import saboroso.saborosoburguer.entities.soldItems.accompaniment.AddOnSale;
import saboroso.saborosoburguer.entities.soldItems.accompaniment.ComboSale;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
public class BurgerSale extends MenuItemSale {
    @ManyToOne
    @JoinColumn(name = "burger_id")
    private Burger soldburger;
    @ManyToOne
    @JoinColumn (name = "bread_id")
    private BurgerBread bread;
    @ManyToOne
    @JoinColumn (name = "combo_sale_id")
    private ComboSale combo;
    @OneToMany
    @JoinTable (
            name = "burger_add_on_sale",
            joinColumns = @JoinColumn(name = "burger_sale_id"),
            inverseJoinColumns = @JoinColumn(name = "add_on_sale_id")
    )
    private List<AddOnSale> addOns;

    @Column
    private String obs;

    public BurgerSale(CustomerOrder orderThatSold, Burger soldburger, BurgerBread bread, ComboSale combo, List<AddOnSale> addOns, String obs) {
        this.soldburger = soldburger;
        this.bread = bread;
        this.combo = combo;
        this.addOns = addOns;
        this.obs = obs;
        setOrderThatSold(orderThatSold);
        setSoldFor(soldburger.getPrice());
    }
}