package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import saboroso.saborosoburguer.entities.soldItems.ComboSale;

public interface ComboSaleRepository extends JpaRepository <ComboSale, Long> {
}
