package saboroso.saborosoburguer.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.BurgerCategory;

@Repository
public interface CategoryRepository extends JpaRepository<BurgerCategory, Long> {
    BurgerCategory findByIdentifier(String identifier);
    List<BurgerCategory> findByDeletedFalse();
    Boolean existsByTitle(String title);
    BurgerCategory findByTitle(String title);
}