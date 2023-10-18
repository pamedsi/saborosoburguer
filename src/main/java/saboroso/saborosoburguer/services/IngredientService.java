package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTO.ingredient.IngredientDTO;
import saboroso.saborosoburguer.entities.menuItems.burger.Ingredient;
import saboroso.saborosoburguer.models.IngredientResponseMessage;
import saboroso.saborosoburguer.repositories.IngredientRepository;
import saboroso.saborosoburguer.DTO.ingredient.IngredientMapper;

import java.time.LocalDateTime;
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
    public IngredientResponseMessage editIngredient(IngredientDTO changes) {
        Boolean alreadyExists = ingredientRepository.existsByTitleAndGramsAndIdentifierNotAndDeletedFalse(
                changes.title(), changes.grams(), changes.identifier()
        );
        if (alreadyExists) return new IngredientResponseMessage(false, "Há outro ingrediente com esses dados!");
        Ingredient ingredientToEdit = ingredientRepository.findByIdentifierAndDeletedFalse(changes.identifier());
        if (ingredientToEdit == null) return new IngredientResponseMessage(false, "Ingrediente não encontrado!");

        int numberOfChanges = 0;

        if(!Objects.equals(ingredientToEdit.getTitle(), changes.title())) {
            numberOfChanges++;
            ingredientToEdit.setTitle(changes.title());
        }
        if(!Objects.equals(ingredientToEdit.getGrams(), changes.grams())) {
            numberOfChanges++;
            ingredientToEdit.setGrams(changes.grams());
        }
        if(ingredientToEdit.getInStock() != changes.inStock()) {
            numberOfChanges++;
            ingredientToEdit.setInStock(changes.inStock());
        }
        if (numberOfChanges == 0) return new IngredientResponseMessage(false, "Nenhuma mudança foi inserida!");

        ingredientToEdit.setLastEdited(LocalDateTime.now());
        ingredientRepository.save(ingredientToEdit);
        return new IngredientResponseMessage(true, null);
    }
    public Boolean removeIngredient(String identifier){
        Ingredient deletedIngredient = ingredientRepository.findByIdentifierAndDeletedFalse(identifier);
        if (deletedIngredient == null) return false;
        if (deletedIngredient.getDeleted()) return false;
        deletedIngredient.setDeleted(true);
        ingredientRepository.save(deletedIngredient);
        return true;
    }
    public Boolean undeleteIngredient(String identifier){
        Ingredient deletedIngredient = ingredientRepository.findByIdentifierAndDeletedTrue(identifier);
        if (deletedIngredient == null) return false;
        deletedIngredient.setDeleted(false);
        ingredientRepository.save(deletedIngredient);
        return true;
    }
    public List<IngredientDTO> getIngredientsForMenu () {
        List<Ingredient> persistenceData = ingredientRepository.getIngredientsByDeletedFalseAndInStockTrue();
        return ingredientMapper.severalToDTO(persistenceData);
    }
}