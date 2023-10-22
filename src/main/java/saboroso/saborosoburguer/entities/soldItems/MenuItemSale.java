package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.soldItems.accompaniment.BaseAccompanimentSale;
@Getter
@Setter
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public abstract class MenuItemSale extends BaseAccompanimentSale {
    @ManyToOne (fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "client_order_id")
    private CustomerOrder orderThatSold;
}