package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.menuItems.Portion;

@Entity
@Table
@NoArgsConstructor
public class PortionSale extends SoldItem {
    @ManyToOne
    @JoinColumn(name = "portion_id")
    private Portion soldPortion;

    public PortionSale(CustomerOrder orderThatSold, Portion soldPortion) {
        this.soldPortion = soldPortion;
        setOrderThatSold(orderThatSold);
        setSingleUnitySoldFor(soldPortion.getPrice());
    }
}