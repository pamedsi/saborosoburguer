package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.ingredient.IngredientDTO;
import saboroso.saborosoburguer.entities.Ingredient;
import saboroso.saborosoburguer.repositories.IngredientRepository;
import saboroso.saborosoburguer.DTOs.ingredient.IngredientMapper;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IngredientService {
    private final IngredientRepository ingredientRepository;
    private final IngredientMapper ingredientMapper;

    public IngredientService(IngredientRepository ingredientRepository, IngredientMapper ingredientMapper) {
        this.ingredientRepository = ingredientRepository;
        this.ingredientMapper = ingredientMapper;
    }
    public Boolean insertIngredient (IngredientDTO ingredientDTO) {
        if (ingredientRepository.existsByTitleAndGrams(ingredientDTO.title(), ingredientDTO.grams())) return false;
        Ingredient newIngredient = new Ingredient(ingredientDTO);
        ingredientRepository.save(newIngredient);
        return true;
    }
    public List<IngredientDTO> getIngredientsForMenuManagement() {
        List<Ingredient> persistenceData = ingredientRepository.getIngredientsByDeletedFalse();
        return ingredientMapper.severalToDTO(persistenceData);
    }
    public Boolean editIngredient(IngredientDTO changes) {
        if (ingredientRepository.existsByTitleAndGrams(changes.title(), changes.grams())) return false;
        Ingredient editedIngredient = ingredientRepository.findByIdentifier(changes.identifier());
        if (changes.grams() != null) editedIngredient.setGrams(changes.grams());
        if (changes.inStock() != null) editedIngredient.setInStock(changes.inStock());
        if (changes.title() != null) editedIngredient.setTitle(changes.title());
        editedIngredient.setLastEdited(LocalDateTime.now());
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
    public List<IngredientDTO> getIngredientsForMenu () {
        List<Ingredient> persistenceData = ingredientRepository.getIngredientsByDeletedFalseAndInStockTrue();
        return ingredientMapper.severalToDTO(persistenceData);
    }
}