package saboroso.saborosoburguer.DTO.category;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.menuItems.burger.BurgerCategory;

import java.util.List;

@Component
public class CategoryMapper {

    public CategoryDTO singleToDTO(BurgerCategory burgerCategory) {
        return new CategoryDTO(
            burgerCategory.getIdentifier(), burgerCategory.getTitle());
    }
    public List<CategoryDTO> severalToDTO(List<BurgerCategory> categoriesPersistence) {
        return categoriesPersistence.stream().map(this::singleToDTO).toList();
    }
}