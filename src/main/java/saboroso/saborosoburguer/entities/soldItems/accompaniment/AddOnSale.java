package saboroso.saborosoburguer.entities.soldItems.accompaniment;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import saboroso.saborosoburguer.entities.menuItems.accompaniment.AddOn;

@Entity
@Table
@NoArgsConstructor
@Getter
public class AddOnSale extends BaseAccompanimentSale {
    @ManyToOne
    @JoinColumn(name = "addon_id")
    private AddOn soldAddOn;

    public AddOnSale(AddOn soldAddOn) {
        this.soldAddOn = soldAddOn;
        setSingleUnitySoldFor(soldAddOn.getPrice());
    }
}
