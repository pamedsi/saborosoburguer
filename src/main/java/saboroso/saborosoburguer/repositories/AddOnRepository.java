package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.menuItems.AddOn;

import java.util.List;

@Repository
public interface AddOnRepository extends JpaRepository<AddOn, Long> {
    List<AddOn> findAllByDeletedFalse();

    Boolean existsByTitleAndIdentifierNotAndDeletedFalse(String title, String identifier);

    AddOn findByIdentifierAndDeletedFalse(String identifier);

    List<AddOn> findAllByDeletedFalseAndInStockTrue();

    AddOn findByIdentifier(String identifier);

    AddOn findByIdentifierAndDeletedFalseAndInStockTrue(String identifier);
}
