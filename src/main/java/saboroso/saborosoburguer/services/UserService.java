package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.address.AddressDTO;
import saboroso.saborosoburguer.DTOs.address.AddressMapper;
import saboroso.saborosoburguer.DTOs.address.InputAddressDTO;
import saboroso.saborosoburguer.DTOs.user.InputUserDTO;
import saboroso.saborosoburguer.entities.Address;
import saboroso.saborosoburguer.entities.UserEntity;
import saboroso.saborosoburguer.repositories.AddressRepository;
import saboroso.saborosoburguer.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public UserService(UserRepository userRepository, AddressRepository addressRepository, AddressMapper addressMapper) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }
    public Boolean addUser(InputUserDTO userDTO) {
        if (userRepository.existsByPhoneNumber(userDTO.phoneNumber())) return false;
        UserEntity newUser = new UserEntity(userDTO);
        userRepository.save(newUser);
        return true;
    }
    public List<AddressDTO> getUserAddresses (String userIdentifier) {
        UserEntity user = userRepository.findByIdentifier(userIdentifier);
        List<Address> addresses = addressRepository.findByBelongsTo(user);
        return addressMapper.severalToDTO(addresses);
    }
}
