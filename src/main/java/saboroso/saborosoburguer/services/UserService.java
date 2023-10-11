package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTO.address.AddressDTO;
import saboroso.saborosoburguer.DTO.address.AddressMapper;
import saboroso.saborosoburguer.DTO.user.UserAdminDTO;
import saboroso.saborosoburguer.DTO.user.UserClientDTO;
import saboroso.saborosoburguer.DTO.user.UserMapper;
import saboroso.saborosoburguer.entities.Address;
import saboroso.saborosoburguer.entities.UserEntity;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.repositories.AddressRepository;
import saboroso.saborosoburguer.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, AddressRepository addressRepository, AddressMapper addressMapper, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
        this.userMapper = userMapper;
    }
    public Boolean addUserAdmin(UserAdminDTO userDTO) {
        if (userRepository.existsByPhoneNumber(userDTO.phoneNumber())) return false;
        UserEntity newUser = new UserEntity(userDTO);
        userRepository.save(newUser);
        return true;
    }
    public Boolean addClientUser(UserClientDTO userDTO) {
        if (userRepository.existsByPhoneNumber(userDTO.phoneNumber())) return false;
        UserEntity newUser = new UserEntity(userDTO);
        userRepository.save(newUser);

        Address address = new Address(newUser, userDTO.address());
        address.setBelongsTo(newUser);
        addressRepository.save(address);
        return true;
    }
    public Boolean addAddressToClient(UserClientDTO userDTO) {
        UserEntity userClient = userRepository.findByPhoneNumber(userDTO.phoneNumber());
        Address address = new Address(userClient, userDTO.address());
        address.setBelongsTo(userClient);
        addressRepository.save(address);
        return true;
    }
    public List<AddressDTO> getUserAddresses (String userIdentifier) {
        UserEntity user = userRepository.findByIdentifier(userIdentifier);
        List<Address> addresses = addressRepository.findByBelongsTo(user);
        return addressMapper.severalToDTO(addresses);
    }

    public UserClientDTO getClientUserByPhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 11) throw new IllegalArgumentException("Número de celular inválido");

        UserEntity user = userRepository.findByPhoneNumber(phoneNumber);
        if (user == null) throw new NullPointerException("Cliente não encontrado");

        return userMapper.singleToClientDTO(user);
    }
}
