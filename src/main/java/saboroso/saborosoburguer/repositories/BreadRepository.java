package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saboroso.saborosoburguer.entities.menuItems.burger.BurgerBread;

import java.util.List;

public interface BreadRepository extends JpaRepository <BurgerBread, Long> {
    List<BurgerBread> findAllByDeletedFalse();

    Boolean existsByTitleAndIdentifierNotAndDeletedFalse(String title, String identifier);

    BurgerBread findByIdentifierAndDeletedFalse(String identifier);

    List<BurgerBread> findAllByDeletedFalseAndInStockTrue();

    BurgerBread findByIdentifierAndDeletedFalseAndInStockTrue(String s);
}
