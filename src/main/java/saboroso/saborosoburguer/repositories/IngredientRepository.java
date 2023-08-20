package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.Ingredient;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByIdentifier(String identifier);
    Boolean existsByTitleAndGrams (String title, Integer grams);
    List<Ingredient> getIngredientsByDeletedFalseAndInStockTrue();
    List<Ingredient> getIngredientsByDeletedFalse();
}
