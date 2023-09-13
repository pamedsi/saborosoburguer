package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.ingredient.IngredientDTO;
import saboroso.saborosoburguer.entities.Ingredient;
import saboroso.saborosoburguer.repositories.IngredientRepository;
import saboroso.saborosoburguer.DTOs.ingredient.IngredientMapper;

import java.util.List;
import java.util.Objects;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public IngredientService(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }
    public Boolean insertIngredient (IngredientDTO ingredientDTO) {
        List<Ingredient> ingredients = ingredientRepository.findAllByTitleAndGramsAndDeletedFalse(ingredientDTO.title(), ingredientDTO.grams());
        if (ingredients.size() > 1) return false;

        if (ingredients.get(0) != null && !ingredients.get(0).getDeleted()) return false;
        if (ingredients.get(0) != null) {
            ingredients.get(0).setDeleted(false);
            ingredientRepository.save(ingredients.get(0));
            return true;
        }
        Ingredient newIngredient = new Ingredient(ingredientDTO);
        ingredientRepository.save(newIngredient);
        return true;
    }
    public List<IngredientDTO> getIngredientsForMenuManagement() {
        List<Ingredient> persistenceData = ingredientRepository.getIngredientsByDeletedFalse();
        return ingredientMapper.severalToDTO(persistenceData);
    }
    public Boolean editIngredient(IngredientDTO changes) {
        List<Ingredient> ingredients = ingredientRepository.findAllByTitleAndGramsAndDeletedFalse(changes.title(), changes.grams());
        if (ingredients.size() > 1) return false;
        Ingredient editedIngredient = ingredients.get(0);
        if(!Objects.equals(editedIngredient.getIdentifier(), changes.identifier())) return false;

        if (changes.grams() != null) editedIngredient.setGrams(changes.grams());
        if (changes.inStock() != null) editedIngredient.setInStock(changes.inStock());
        if (changes.title() != null) editedIngredient.setTitle(changes.title());
        ingredientRepository.save(editedIngredient);
        return true;
    }
    public Boolean removeIngredient(String identifier){
        Ingredient deletedIngredient = ingredientRepository.findByIdentifier(identifier);
        if (deletedIngredient == null) return false;
        if (deletedIngredient.getDeleted()) return false;
        deletedIngredient.setDeleted(true);
        ingredientRepository.save(deletedIngredient);
        return true;
    }
    public Boolean undeleteIngredient(String identifier){
        Ingredient deletedIngredient = ingredientRepository.findByIdentifier(identifier);
        if (!deletedIngredient.getDeleted()) return false;
        deletedIngredient.setDeleted(false);
        ingredientRepository.save(deletedIngredient);
        return true;
    }
    public List<IngredientDTO> getIngredientsForMenu () {
        List<Ingredient> persistenceData = ingredientRepository.getIngredientsByDeletedFalseAndInStockTrue();
        return ingredientMapper.severalToDTO(persistenceData);
    }
}