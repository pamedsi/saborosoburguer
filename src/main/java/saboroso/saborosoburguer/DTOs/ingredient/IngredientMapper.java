package saboroso.saborosoburguer.DTOs.ingredient;


import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientMapper {
    public IngredientDTO singleIngredientMapper (Ingredient ingredient) {
        return new IngredientDTO(
                        ingredient.getIdentifier(),
                        ingredient.getTitle(),
                        ingredient.getGrams(),
                        ingredient.getInStock(),
                        ingredient.getDeleted()
                        );
    }
    public List<IngredientDTO> severalToDTO (List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(this::singleIngredientMapper)
                .collect(Collectors.toList());
    }
}