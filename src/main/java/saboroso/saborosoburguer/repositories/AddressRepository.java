package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.Address;
import saboroso.saborosoburguer.entities.UserEntity;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository <Address, Long> {
    List<Address> findByBelongsTo(UserEntity user);

    Address findByContent(String content);
}
