package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.Burger;

import java.util.List;

@Repository
public interface BurgerRepository extends JpaRepository <Burger, Long> {
    Boolean existsBurgerByTitle(String title);
    Burger findBurgerByIdentifier(String identifier);
    default Boolean hasIngredient(Burger burger, String ingredientIdentifier) {
        return burger.getIngredients().stream()
                .anyMatch(ingredient -> ingredient.getIdentifier().equals(ingredientIdentifier));
    }
    List<Burger> findBurgerByDeletedFalseAndInStockTrue();
    Burger findSingleById(Long burgerId);
}