package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.menuItems.Portion;
import saboroso.saborosoburguer.entities.soldItems.accompaniment.AddOnSale;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@Getter
public class PortionSale extends MenuItemSale {
    @ManyToOne
    @JoinColumn(name = "portion_id")
    private Portion soldPortion;
    @OneToMany
    @JoinTable (
            name = "portion_add_on_sale",
            joinColumns = @JoinColumn(name = "portion_sale_id"),
            inverseJoinColumns = @JoinColumn(name = "add_on_sale_id")
    )
    private List<AddOnSale> addOns;

    @Column
    private String obs;

    public PortionSale(CustomerOrder orderThatSold, Portion soldPortion, List<AddOnSale> addOns, String obs) {
        this.soldPortion = soldPortion;
        this.addOns = addOns;
        setOrderThatSold(orderThatSold);
        setSoldFor(soldPortion.getPrice());
        this.obs = obs;
    }
}