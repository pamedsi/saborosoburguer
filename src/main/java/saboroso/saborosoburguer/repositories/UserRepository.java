package saboroso.saborosoburguer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import saboroso.saborosoburguer.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository <UserEntity, Long>{
    UserEntity findByIdentifier (String identifier) ;
}
