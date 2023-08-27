package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.CustomerOrder;

@Repository
public interface CostumerOrderRepository extends JpaRepository<CustomerOrder, Long> {

}
