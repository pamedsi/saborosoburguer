package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.ClientOrder;
import saboroso.saborosoburguer.entities.soldItems.DrinkSale;

@Repository
public interface DrinkSaleRepository extends JpaRepository <DrinkSale, Long> {
    DrinkSale findByOrderThatSold(ClientOrder orderThatSold);

}
