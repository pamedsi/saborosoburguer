package saboroso.saborosoburguer.DTOs;


import saboroso.saborosoburguer.entities.Ingredient;

import java.util.List;
import java.util.stream.Collectors;

public class IngredientMapper {
    public static List<IngredientForMenu> ingredientForMenuMapper (List<Ingredient> ingredients) {
        return ingredients.stream()
                .map(ingredient -> new IngredientForMenu(ingredient.getTitle(), ingredient.getGrams()))
                .collect(Collectors.toList());
    }
}