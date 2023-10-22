package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.soldItems.BurgerSale;

import java.util.List;

@Repository
public interface BurgerSaleRepository extends JpaRepository <BurgerSale, Long> {
    List<BurgerSale> findAllByOrderThatSold(CustomerOrder orderThatSold);
    @Query(value = "SELECT burger_id, SUM(quantity) as total_sales FROM burger_sale GROUP BY burger_id ORDER BY total_sales DESC LIMIT 2;", nativeQuery = true )
    // Vem do banco de dados assim: ["4,8", "7,10"]
    // ["id_do_hamburguer1, vendas_do_hamburguer1", "id_do_hamburguer2, vendas_do_hamburguer2"]
    String[] getMostSold();
}