package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTO.address.AddressDTO;
import saboroso.saborosoburguer.DTO.address.AddressMapper;
import saboroso.saborosoburguer.DTO.user.UserAdminDTO;
import saboroso.saborosoburguer.DTO.user.UserClientDTO;
import saboroso.saborosoburguer.DTO.user.UserMapper;
import saboroso.saborosoburguer.entities.Address;
import saboroso.saborosoburguer.entities.UserEntity;
import saboroso.saborosoburguer.models.UserAndAddress;
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
    public UserAndAddress addClientUser(String name, String phoneNumber, String address) {
        if (userRepository.existsByPhoneNumber(phoneNumber)) return null;
        UserEntity newUser = new UserEntity(name, phoneNumber);
        userRepository.save(newUser);

        Address newAddress = new Address(newUser, address);
        newAddress.setBelongsTo(newUser);
        addressRepository.save(newAddress);
        return new UserAndAddress(newUser, newAddress);
    }
    public Address addAddressToClient(UserEntity user, String address) {
        Address newAddress = new Address(user, address);
        newAddress.setBelongsTo(user);
        addressRepository.save(newAddress);
        return newAddress;
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
