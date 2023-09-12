package saboroso.saborosoburguer.DTOs.category;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.BurgerCategory;

import java.util.List;

@Component
public class CategoryMapper {

    public List<CategoryDTO> severalToDTO(List<BurgerCategory> categoriesPersistence) {
        return categoriesPersistence.stream().map(category -> new CategoryDTO(category.getIdentifier(), category.getTitle(), category.getDeleted())).toList();
    }
}
