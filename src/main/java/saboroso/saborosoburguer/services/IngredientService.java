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
        if (!ingredients.isEmpty()) return false;

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
        Ingredient ingredientToEdit;
        if (ingredients.size() > 1) return false;
        if (ingredients.size() == 1 && !Objects.equals(ingredients.get(0).getIdentifier(), changes.identifier())) {
            return false;
        }
        if (ingredients.size() == 1) ingredientToEdit = ingredients.get(0);
        else ingredientToEdit = ingredientRepository.findByIdentifier(changes.identifier());
        if(ingredientToEdit == null) return false;

        if (changes.grams() != null) ingredientToEdit.setGrams(changes.grams());
        if (changes.inStock() != null) ingredientToEdit.setInStock(changes.inStock());
        if (changes.title() != null) ingredientToEdit.setTitle(changes.title());
        ingredientRepository.save(ingredientToEdit);
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