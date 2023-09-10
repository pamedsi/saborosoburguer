package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.BurgerCategory;

@Repository
public interface CategoryRepository extends JpaRepository<BurgerCategory, Long> {
    BurgerCategory findByIdentifier(String identifier);

    Boolean existsByTitle(String title);
}