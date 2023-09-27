package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.Portion;

import java.util.List;

@Repository
public interface PortionRepository extends JpaRepository <Portion, Long> {
    List<Portion> findByDeletedFalseAndInStockTrue();
    Portion findByIdentifier(String identifier);
    List<Portion> findAllByDeletedFalse();

    Boolean existsByTitleAndIdentifierNotAndDeletedFalse(String title, String identifier);

    Portion findByIdentifierAndDeletedFalse(String identifier);

    List<Portion> findAllByDeletedFalseAndInStockTrue();
}
