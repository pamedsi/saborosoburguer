package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.Combo;

import java.util.List;

@Repository
public interface ComboRepository extends JpaRepository<Combo, Long> {
    Combo findByIdentifier(String identifier);

    Combo findByTitle(String identifier);

    List<Combo> findAllByDeletedFalse();

    Boolean existsByTitleAndIdentifierNot(String title, String identifier);

    Boolean existsByTitleAndIdentifierNotAndDeletedFalse(String title, String identifier);

    Combo findByIdentifierAndDeletedFalse(String comboIdentifier);

    List<Combo> findAllByDeletedFalseAndInStockTrue();
}
