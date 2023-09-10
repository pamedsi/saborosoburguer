package saboroso.saborosoburguer.DTOs.burger;

import org.springframework.stereotype.Component;

import saboroso.saborosoburguer.DTOs.category.CategoryDTO;
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
                persistenceBurger.getBurgerCategory().getTitle(),
                persistenceBurger.getTitle(),
                persistenceBurger.getPrice(),
                persistenceBurger.getPic(),
                ingredientMapper.ingredientsForMenuMapper(persistenceBurger.getIngredients()));
    }
    public BurgerManagementDTO singleBurgerForManagement (Burger persistenceBurger) {
                return new BurgerManagementDTO(
                persistenceBurger.getIdentifier(),
                new CategoryDTO(persistenceBurger.getBurgerCategory().getIdentifier(), persistenceBurger.getBurgerCategory().getTitle()),
                persistenceBurger.getTitle(),
                persistenceBurger.getPrice(),
                persistenceBurger.getPic(),
                persistenceBurger.getInStock(),
                ingredientMapper.ingredientsForMenuMapper(persistenceBurger.getIngredients()));
    }
    public List<BurgerManagementDTO> burgersForManagementMapper (List<Burger> burgers) {
        return burgers.stream()
                .map(this::singleBurgerForManagement)
                .collect(Collectors.toList());
    }
    public List<BurgerForMenuDTO> burgersForMenuMapper (List<Burger> burgers) {
        return burgers.stream()
                .map(this::singleBurgerForMenuDTO)
                .collect(Collectors.toList());
    }
}