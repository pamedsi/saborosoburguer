package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.ClientOrder;

@Repository
public interface ClientOrderRepository extends JpaRepository<ClientOrder, Long> {

}
