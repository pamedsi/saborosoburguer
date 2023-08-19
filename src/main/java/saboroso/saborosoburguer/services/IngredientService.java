package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.inputIngredientDTO;
import saboroso.saborosoburguer.entities.Ingredient;
import saboroso.saborosoburguer.repositories.IngredientRepository;

import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    public Boolean insertIngredient (inputIngredientDTO ingredientDTO) {
        if (ingredientRepository.existsByTitleAndGrams(ingredientDTO.title(), ingredientDTO.grams())) return false;
        Ingredient newIngredient = new Ingredient(ingredientDTO);
        ingredientRepository.save(newIngredient);
        return true;
    }
    public List<Ingredient> getIngredients () {
        return ingredientRepository.findAll();
    }
    public Boolean editIngredient(inputIngredientDTO changes, String identifier) {
        if (ingredientRepository.existsByTitleAndGrams(changes.title(), changes.grams())) return false;
        Ingredient editedIngredient = ingredientRepository.findByIdentifier(identifier);
        if (changes.grams() != null) editedIngredient.setGrams(changes.grams());
        if (changes.inStock() != null) editedIngredient.setGrams(changes.grams());
        if (changes.title() != null) editedIngredient.setTitle(changes.title());
        ingredientRepository.save(editedIngredient);
        return true;
    }
    public Boolean removeIngredient(String identifier){
        Ingredient deletedIngredient = ingredientRepository.findByIdentifier(identifier);
        if (deletedIngredient.getDeleted()) return false;
        deletedIngredient.setDeleted(true);
        return true;
    }
    public Boolean undeleteIngredient(String identifier){
        Ingredient deletedIngredient = ingredientRepository.findByIdentifier(identifier);
        if (!deletedIngredient.getDeleted()) return false;
        deletedIngredient.setDeleted(false);
        return true;
    }
}
