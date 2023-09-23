package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.addOn.AddOnDTO;
import saboroso.saborosoburguer.DTOs.addOn.AddOnMapper;
import saboroso.saborosoburguer.entities.AddOn;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.repositories.AddOnRepository;

import java.util.List;

@Service
public class AddOnService {

    private final AddOnRepository addOnRepository;
    private final AddOnMapper addOnMapper;

    private AddOnService (AddOnRepository addOnRepository, AddOnMapper addOnMapper) {
        this.addOnRepository = addOnRepository;
        this.addOnMapper = addOnMapper;
    }
    public CRUDResponseMessage createAddOn(AddOnDTO addOnDTO){
        AddOn newAddon = new AddOn(addOnDTO);
        addOnRepository.save(newAddon);
        return new CRUDResponseMessage(true, null, List.of(addOnDTO.title() + " adicionado."));
    }

    public List<AddOnDTO> getAllAddOns() {
        List<AddOn> addOnsPersistence = addOnRepository.findAllByDeletedFalse();
        return addOnMapper.severalToDTO(addOnsPersistence);
    }
}
