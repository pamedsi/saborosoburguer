package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.menuItems.burger.Ingredient;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByIdentifierAndDeletedFalse(String identifier);
    Boolean existsByTitleAndGramsAndIdentifierNotAndDeletedFalse (String title, Integer grams, String identifier);
    List<Ingredient> getIngredientsByDeletedFalseAndInStockTrue();
    List<Ingredient> getIngredientsByDeletedFalse();
    List<Ingredient> findAllByTitleAndGramsAndDeletedFalse(String title, Integer grams);
    Ingredient findByIdentifierAndDeletedTrue(String identifier);
}
