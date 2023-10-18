package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import saboroso.saborosoburguer.entities.menuItems.Combo;
import saboroso.saborosoburguer.entities.CustomerOrder;

@Entity
@Table
@NoArgsConstructor
public class ComboSale extends SoldItem{
    @ManyToOne
    @JoinColumn(name = "combo_id")
    private Combo soldCombo;

    public ComboSale(CustomerOrder orderThatSold, Combo soldCombo, Long quantity) {
        this.soldCombo = soldCombo;
        setOrderThatSold(orderThatSold);
        setQuantity(quantity);
        setSingleUnitySoldFor(soldCombo.getPrice());
    }
}