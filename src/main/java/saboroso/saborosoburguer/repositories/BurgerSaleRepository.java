package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.ClientOrder;
import saboroso.saborosoburguer.entities.soldItems.BurgerSale;

@Repository
public interface BurgerSaleRepository extends JpaRepository <BurgerSale, Long> {
    BurgerSale findByOrderThatSold(ClientOrder orderThatSold);
    @Query(value = "SELECT burger_id, SUM(amount) as total_sales FROM burger_sale GROUP BY burger_id ORDER BY total_sales DESC LIMIT 1;", nativeQuery = true )
    String getMostSold();
}