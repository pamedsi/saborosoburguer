package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.*;
import saboroso.saborosoburguer.DTOs.ingredient.inputIngredientDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Data
public class Ingredient {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Long id;
    @Setter(AccessLevel.NONE)
    private String identifier;
    @Column (nullable = false)
    private String title;
    @Column (nullable = true)
    private Integer grams;
    @Column
    private Boolean inStock;
    @Column
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;
    @Column
    private Boolean deleted;

    public Ingredient(inputIngredientDTO ingredientDTO) {
        identifier = UUID.randomUUID().toString();
        title = ingredientDTO.title();
        if (ingredientDTO.grams() != null) grams = ingredientDTO.grams();
        if (ingredientDTO.inStock() != null) inStock = ingredientDTO.inStock();
        else inStock = true;
        createdAt = LocalDateTime.now();
        deleted = false;
    }
}