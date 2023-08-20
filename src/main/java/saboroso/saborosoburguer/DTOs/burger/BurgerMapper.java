package saboroso.saborosoburguer.DTOs.burger;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.DTOs.ingredient.IngredientForMenuDTO;
import saboroso.saborosoburguer.DTOs.ingredient.IngredientMapper;
import saboroso.saborosoburguer.entities.Burger;
import saboroso.saborosoburguer.entities.Ingredient;
import saboroso.saborosoburguer.repositories.IngredientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BurgerMapper {
    private final IngredientMapper ingredientMapper;
    public BurgerMapper(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }
    public List<BurgerForMenuDTO> burgersForMenuMapper (List<Burger> burgers) {
        return burgers.stream()
                .map(burger -> new BurgerForMenuDTO(
                        burger.getIdentifier(),
                        burger.getCategory(),
                        burger.getTitle(),
                        burger.getPrice(),
                        burger.getPic(),
                        ingredientMapper.ingredientsForMenuMapper(burger.getIngredients())))
                .collect(Collectors.toList());
    }
}
