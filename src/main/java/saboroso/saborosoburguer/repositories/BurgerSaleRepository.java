package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;
import saboroso.saborosoburguer.entities.ClientOrder;
import saboroso.saborosoburguer.entities.soldItems.BurgerSale;

@Repository
public interface BurgerSaleRepository extends JpaRepository <BurgerSale, Long> {
    BurgerSale findByOrderThatSold(ClientOrder orderThatSold);
}
