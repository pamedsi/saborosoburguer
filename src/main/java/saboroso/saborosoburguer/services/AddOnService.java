package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.addOn.AddOnDTO;
import saboroso.saborosoburguer.DTOs.addOn.AddOnMapper;
import saboroso.saborosoburguer.entities.AddOn;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.repositories.AddOnRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    public CRUDResponseMessage editAddOn(AddOnDTO changes) {
        Boolean alreadyExists = addOnRepository.existsByTitleAndIdentifierNotAndDeletedFalse(changes.title(), changes.identifier());
        if (alreadyExists) return new CRUDResponseMessage(false, "Já existe um adicional com esse nome!", null);
        AddOn addOnToEdit = addOnRepository.findByIdentifierAndDeletedFalse(changes.identifier());
        if (addOnToEdit == null) return new CRUDResponseMessage(false, "Adicional não encontrado!", null);

        List<String> changesForResponse = new ArrayList<>();

        if (!Objects.equals(addOnToEdit.getTitle(), changes.title())) {
            addOnToEdit.setTitle(changes.title());
            changesForResponse.add("Título modificado! Agora se chama: " + changes.title());
        }
        if (addOnToEdit.getPrice().compareTo(changes.price()) != 0) {
            addOnToEdit.setPrice(changes.price());
            changesForResponse.add("Preço alterado! Agora custa: " + changes.price());
        }
        if (addOnToEdit.getInStock() != changes.inStock()) {
            addOnToEdit.setInStock(changes.inStock());
            if (changes.inStock()) changesForResponse.add(changes.title() + " disponibilizado!");
            else changesForResponse.add(changes.title() + " indisponibilizado!");
        }

        if (changesForResponse.isEmpty()) return new CRUDResponseMessage(false, "Nenhuma mudança solicitada é diferente dos dados já presentes!", null);
        addOnRepository.save(addOnToEdit);
        return new CRUDResponseMessage(true, null, changesForResponse);
    }
    public CRUDResponseMessage deleteAddOn(String addOnIdentifier) {
        if (addOnIdentifier == null || addOnIdentifier.length() != 36) return new CRUDResponseMessage(false, "Identificador de adicional inválido!", null);
        AddOn addOnToDelete = addOnRepository.findByIdentifierAndDeletedFalse(addOnIdentifier);
        if (addOnToDelete == null) return new CRUDResponseMessage(false, "Adicional não encontrado!", null);

        addOnToDelete.setDeleted(true);
        addOnRepository.save(addOnToDelete);
        return new CRUDResponseMessage(true, null, List.of(addOnToDelete.getTitle() + " deletado!"));
    }
    public List<AddOnDTO> getAddOnsForMenu() {
        List<AddOn> addOns = addOnRepository.findAllByDeletedFalseAndInStockTrue();
        return addOnMapper.severalToDTO(addOns);
    }
}
