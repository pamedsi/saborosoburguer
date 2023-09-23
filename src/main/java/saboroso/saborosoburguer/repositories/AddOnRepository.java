package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.AddOn;

import java.util.List;

@Repository
public interface AddOnRepository extends JpaRepository<AddOn, Long> {
    List<AddOn> findAllByDeletedFalse();
}