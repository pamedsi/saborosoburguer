package saboroso.saborosoburguer.DTO.burger;

import org.springframework.stereotype.Component;

import saboroso.saborosoburguer.DTO.category.CategoryDTO;
import saboroso.saborosoburguer.DTO.ingredient.IngredientMapper;
import saboroso.saborosoburguer.entities.menuItems.burger.Burger;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BurgerMapper {
    private final IngredientMapper ingredientMapper;
    public BurgerMapper(IngredientMapper ingredientMapper) {
        this.ingredientMapper = ingredientMapper;
    }

    public BurgerDTO singleToDTO(Burger persistenceBurger) {
                return new BurgerDTO(
                persistenceBurger.getIdentifier(),
                    new CategoryDTO(
                        persistenceBurger.getBurgerCategory().getIdentifier(),
                        persistenceBurger.getBurgerCategory().getTitle()
                    ),
                    persistenceBurger.getTitle(),
                    persistenceBurger.getPrice(),
                    persistenceBurger.getPic(),
                    persistenceBurger.getInStock(),
                    ingredientMapper.severalToDTO(persistenceBurger.getIngredients()
                    ));
    }
    public List<BurgerDTO> severalToDTO(List<Burger> burgers) {
        return burgers.stream()
                .map(this::singleToDTO)
                .collect(Collectors.toList());
    }



}