package saboroso.saborosoburguer.entities.menuItems;

import jakarta.persistence.*;
import lombok.*;
import saboroso.saborosoburguer.DTO.drink.DrinkDTO;

@Entity
@Table
@NoArgsConstructor
@Getter
public class Drink extends MenuItem {
    @Column
    @Setter
    private Integer ml;

    public Drink(DrinkDTO drinkDTO) {
        setTitle(drinkDTO.title());
        setPrice(drinkDTO.price());
        setInStock(drinkDTO.inStock());
        setPic(drinkDTO.pic());
        setMl(drinkDTO.ml());
    }
}