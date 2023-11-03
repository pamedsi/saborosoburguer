package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.CustomerOrder;

import java.util.List;

@Repository
public interface CostumerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    CustomerOrder findByIdentifier(String s);

    @Query("SELECT c FROM CustomerOrder c WHERE c.orderStatus != 'DELIVERED' AND c.orderStatus != 'CANCELED'")
    List<CustomerOrder> findUnfinishedOrders();

}
