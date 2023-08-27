package saboroso.saborosoburguer.DTOs.address;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.Address;

import java.util.List;

@Component
public class AddressMapper {
    public AddressDTO singleAddressToDTO(Address address) {
        return new AddressDTO(address.getContent());
    }
    public List<AddressDTO> severalToDTO(List<Address> addresses) {
        return addresses.stream().map(address -> new AddressDTO(address.getContent())).toList();
    }
}
