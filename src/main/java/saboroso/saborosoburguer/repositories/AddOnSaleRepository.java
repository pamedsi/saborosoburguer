package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saboroso.saborosoburguer.entities.soldItems.accompaniment.AddOnSale;

public interface AddOnSaleRepository extends JpaRepository<AddOnSale, Long> {
}
