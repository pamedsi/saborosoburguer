package saboroso.saborosoburguer.entities.soldItems;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import saboroso.saborosoburguer.entities.Burger;
import saboroso.saborosoburguer.entities.ClientOrder;

@Entity
@Table
@Data
@NoArgsConstructor
public class BurgerSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "client_order_id")
    private ClientOrder orderThatSold;
    @ManyToOne
    @JoinColumn(name = "burger_id")
    private Burger soldburger;
    @Column
    private Integer amount;
    public BurgerSale(ClientOrder orderThatSold, Burger soldburger, Integer amount) {
        this.orderThatSold = orderThatSold;
        this.soldburger = soldburger;
        this.amount = amount;
    }
}