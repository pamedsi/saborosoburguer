package saboroso.saborosoburguer.DTOs.ingredient;


import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class IngredientMapper {
    public List<IngredientForMenuDTO> ingredientsForMenuMapper (List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredient -> new IngredientForMenuDTO(ingredient.getTitle(), ingredient.getGrams()))
                .collect(Collectors.toList());
    }

    public List<IngredientForMenuManagementDTO> ingredientsForMenuManagementMapper (List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredient -> new IngredientForMenuManagementDTO(
                        ingredient.getIdentifier(),
                        ingredient.getTitle(),
                        ingredient.getGrams()
                        ))
                .collect(Collectors.toList());
    }
}