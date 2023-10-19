package saboroso.saborosoburguer.entities.menuItems.burger;

import jakarta.persistence.*;
import lombok.*;
import saboroso.saborosoburguer.DTO.ingredient.IngredientDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table
@NoArgsConstructor
public class Ingredient extends BurgerComponent{
    @Column
    @Setter
    private Integer grams;
    public Ingredient(IngredientDTO ingredientDTO) {
        setTitle(ingredientDTO.title());
        grams = ingredientDTO.grams();
        if (ingredientDTO.inStock() != null) setInStock(ingredientDTO.inStock());
        else setInStock(true);
    }
}