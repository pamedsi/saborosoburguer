package saboroso.saborosoburguer.DTO.user;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.Address;
import saboroso.saborosoburguer.entities.UserEntity;
import saboroso.saborosoburguer.repositories.AddressRepository;

import java.util.List;

@Component
public class UserMapper {
    private final AddressRepository addressRepository;

    public UserMapper(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public UserClientDTO singleToClientDTO(UserEntity user){
        List<Address> addressesEntity = addressRepository.findByBelongsTo(user);
        List<String> addressesString = addressesEntity.stream().map(Address::getContent).toList();
        return new UserClientDTO(
                user.getIdentifier(),
                user.getName(),
                user.getPhoneNumber(),
                null,
                addressesString
        );
    }


}
