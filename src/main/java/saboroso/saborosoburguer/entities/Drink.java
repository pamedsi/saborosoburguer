package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.*;
import saboroso.saborosoburguer.DTOs.drink.InputDrinkDTO;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table
public class Drink {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter (AccessLevel.NONE)
    @Id
    private Long id;
    @Column
    @Setter(AccessLevel.NONE)
    private String identifier = UUID.randomUUID().toString();
    @Column
    private String title;
    @Column
    private BigDecimal price;
    @Column
    private Boolean inStock;
    @Column
    private Boolean deleted;
    @Column
    private Integer ml;

    public Drink(InputDrinkDTO inputDrinkDTO) {
        title = inputDrinkDTO.title();
        price = inputDrinkDTO.price();
        if (inputDrinkDTO.inStock() != null) setInStock(inputDrinkDTO.inStock());
        else setInStock(true);
        if (inputDrinkDTO.deleted() != null) setDeleted(inputDrinkDTO.deleted());
        else setDeleted(false);
        ml = inputDrinkDTO.ml();
    }
}
