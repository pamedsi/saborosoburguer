package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.soldItems.DrinkSale;

import java.util.List;

@Repository
public interface DrinkSaleRepository extends JpaRepository <DrinkSale, Long> {
    DrinkSale findByOrderThatSold(CustomerOrder orderThatSold);

    List<DrinkSale> findAllByOrderThatSold(CustomerOrder order);
}
