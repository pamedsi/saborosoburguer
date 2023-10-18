package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import saboroso.saborosoburguer.entities.menuItems.AddOn;
import saboroso.saborosoburguer.entities.CustomerOrder;

@Entity
@Table
@NoArgsConstructor
public class AddOnSale extends SoldItem{
    @ManyToOne
    @JoinColumn(name = "addon_id")
    private AddOn soldAddOn;

    public AddOnSale(CustomerOrder orderThatSold, AddOn soldAddOn, Long quantity) {
        this.soldAddOn = soldAddOn;
        setOrderThatSold(orderThatSold);
        setQuantity(quantity);
        setSingleUnitySoldFor(soldAddOn.getPrice());
    }
}
