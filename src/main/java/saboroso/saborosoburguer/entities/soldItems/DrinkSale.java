package saboroso.saborosoburguer.entities.soldItems;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import saboroso.saborosoburguer.entities.ClientOrder;
import saboroso.saborosoburguer.entities.Drink;

@Entity
@Table
@NoArgsConstructor
@Data
public class DrinkSale {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "client_order_id")
    private ClientOrder orderThatSold;
    @ManyToOne
    @JoinColumn(name = "drink_id")
    private Drink soldDrink;
    @Column
    private Long amount;
    public DrinkSale(ClientOrder orderThatSold, Drink soldDrink, Long amount) {
        this.orderThatSold = orderThatSold;
        this.soldDrink = soldDrink;
        this.amount = amount;
    }
}