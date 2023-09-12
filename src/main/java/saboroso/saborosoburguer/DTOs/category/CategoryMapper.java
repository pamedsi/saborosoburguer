package saboroso.saborosoburguer.DTOs.category;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.BurgerCategory;

import java.util.List;

@Component
public class CategoryMapper {

    public CategoryDTO singleToDTO(BurgerCategory burgerCategory) {
        return new CategoryDTO(
            burgerCategory.getIdentifier(), burgerCategory.getTitle(), burgerCategory.getDeleted());
    }

    public List<CategoryDTO> severalToDTO(List<BurgerCategory> categoriesPersistence) {
        return categoriesPersistence.stream().map(this::singleToDTO).toList();
    }
}