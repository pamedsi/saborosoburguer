package saboroso.saborosoburguer.entities.soldItems;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import lombok.Setter;
import saboroso.saborosoburguer.entities.ClientOrder;
import saboroso.saborosoburguer.entities.Portion;

@Entity
@Table
@NoArgsConstructor
public class PortionSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_order_id")
    private ClientOrder orderThatSold;
    @ManyToOne
    @JoinColumn(name = "portion_id")
    private Portion soltPortion;
    @Column
    private Long amount;
    public PortionSale(ClientOrder orderThatSold, Portion soltPortion, Long amount) {
        this.orderThatSold = orderThatSold;
        this.soltPortion = soltPortion;
        this.amount = amount;
    }
}
