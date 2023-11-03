package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.CustomerOrder;
import saboroso.saborosoburguer.entities.UserEntity;

import java.util.List;

@Repository
public interface CostumerOrderRepository extends JpaRepository<CustomerOrder, Long> {

    CustomerOrder findByIdentifier(String identifier);

    @Query("SELECT c FROM CustomerOrder c WHERE c.orderStatus != 'DELIVERED' AND c.orderStatus != 'CANCELED'")
    List<CustomerOrder> findUnfinishedOrders();

    @Query("SELECT c FROM CustomerOrder c WHERE c.orderCode = :orderCode ORDER BY c.timeOfPurchase ASC")
    List<CustomerOrder> findAllByOrderCode(@Param("orderCode") String orderCode);

    @Query("SELECT c FROM CustomerOrder c WHERE c.orderStatus != 'DELIVERED' AND c.orderStatus != 'CANCELED' AND c.clientWhoOrdered = :customer")
    List<CustomerOrder> findByClientWhoOrdered(@Param("customer") UserEntity customer);
}
