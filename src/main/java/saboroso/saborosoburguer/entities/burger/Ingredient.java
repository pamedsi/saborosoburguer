package saboroso.saborosoburguer.entities.burger;

import jakarta.persistence.*;
import lombok.*;
import saboroso.saborosoburguer.DTO.ingredient.IngredientDTO;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table
public class Ingredient {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Long id;
    @Setter(AccessLevel.NONE)
    private String identifier;
    @Column (nullable = false)
    private String title;
    @Column
    private Integer grams;
    @Column
    private Boolean inStock;
    @Column
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;
    @Column
    private Boolean deleted;
    @Column
    private LocalDateTime lastEdited;

    public Ingredient(IngredientDTO ingredientDTO) {
        identifier = UUID.randomUUID().toString();
        title = ingredientDTO.title();
        if (ingredientDTO.grams() != null) grams = ingredientDTO.grams();
        if (ingredientDTO.inStock() != null) inStock = ingredientDTO.inStock();
        else inStock = true;
        createdAt = LocalDateTime.now();
        deleted = false;
    }
    public Ingredient() {
        identifier = UUID.randomUUID().toString();
        createdAt = LocalDateTime.now();
    }
}