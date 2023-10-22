package saboroso.saborosoburguer.entities.soldItems.accompaniment;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

import saboroso.saborosoburguer.entities.menuItems.accompaniment.Combo;

@Entity
@Table
@NoArgsConstructor
@Getter
public class ComboSale extends BaseAccompanimentSale {
    @ManyToOne
    @JoinColumn(name = "combo_id")
    private Combo soldCombo;

    public ComboSale(Combo soldCombo) {
        this.soldCombo = soldCombo;
        setSingleUnitySoldFor(soldCombo.getPrice());
    }
}