package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.Drink;
import saboroso.saborosoburguer.entities.Portion;

import java.util.List;

@Repository
public interface DrinkRepository extends JpaRepository <Drink, Long> {
    List<Drink> findByDeletedFalseAndInStockTrue();
    Boolean existsByTitleAndMl(String title, Integer mL);
    List<Drink> findAllByDeletedFalse();
    Boolean existsByTitleAndMlAndIdentifierNotAndDeletedFalse(String title, Integer ml, String identifier);
    Drink findByIdentifierAndDeletedFalse(String identifier);

    Drink findByIdentifier(String drinkIdentifier);
}
