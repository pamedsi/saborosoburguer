package saboroso.saborosoburguer.DTOs.burger;

import org.springframework.stereotype.Component;

import saboroso.saborosoburguer.DTOs.ingredient.IngredientMapper;
import saboroso.saborosoburguer.entities.Burger;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BurgerMapper {
    private final IngredientMapper ingredientMapper;
    public BurgerMapper(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }
    public BurgerForMenuDTO singleBurgerForMenuDTO (Burger persistenceBurger) {
        return new BurgerForMenuDTO(
                persistenceBurger.getIdentifier(),
                persistenceBurger.getCategory(),
                persistenceBurger.getTitle(),
                persistenceBurger.getPrice(),
                persistenceBurger.getPic(),
                ingredientMapper.ingredientsForMenuMapper(persistenceBurger.getIngredients()));
    }
    public List<BurgerForMenuDTO> burgersForMenuMapper (List<Burger> burgers) {
        return burgers.stream()
                .map(this::singleBurgerForMenuDTO)
                .collect(Collectors.toList());
    }
}