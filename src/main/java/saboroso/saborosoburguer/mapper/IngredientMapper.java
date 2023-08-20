package saboroso.saborosoburguer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import saboroso.saborosoburguer.DTOs.ingredient.IngredientForMenuDTO;
import saboroso.saborosoburguer.DTOs.ingredient.inputIngredientDTO;
import saboroso.saborosoburguer.entities.Ingredient;

@Mapper
public interface IngredientMapper {
    IngredientMapper INSTANCE = Mappers.getMapper(IngredientMapper.class);
    Ingredient toModel(inputIngredientDTO ingredientDTO);
    IngredientForMenuDTO toDTO(Ingredient persistenceIngredient);
}
