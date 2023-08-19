package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saboroso.saborosoburguer.entities.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
