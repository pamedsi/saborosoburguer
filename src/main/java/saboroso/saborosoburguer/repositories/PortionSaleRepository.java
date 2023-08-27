package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.soldItems.PortionSale;

@Repository
public interface PortionSaleRepository extends JpaRepository <PortionSale, Long> {
    PortionSale findByOrderThatSold(CustomerOrder orderThatSold);
}
