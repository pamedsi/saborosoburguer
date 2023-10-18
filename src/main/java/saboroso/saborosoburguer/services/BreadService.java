package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTO.burger.BreadMapper;
import saboroso.saborosoburguer.DTO.burger.BurgerBreadDTO;
import saboroso.saborosoburguer.entities.menuItems.burger.BurgerBread;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.repositories.BreadRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BreadService {

    private final BreadRepository breadRepository;
    private final BreadMapper breadMapper;

    public BreadService (BreadRepository breadRepository, BreadMapper breadMapper) {
        this.breadRepository = breadRepository;
        this.breadMapper = breadMapper;
    }
    public CRUDResponseMessage createBread(BurgerBreadDTO burgerBreadDTO){
        BurgerBread newBread = new BurgerBread(burgerBreadDTO);
        breadRepository.save(newBread);
        return new CRUDResponseMessage(true, null, List.of(burgerBreadDTO.title() + " adicionado."));
    }
    public List<BurgerBreadDTO> getAllBreads() {
        List<BurgerBread> breadsPersistence = breadRepository.findAllByDeletedFalse();
        return breadMapper.severalToDTO(breadsPersistence);
    }
    public CRUDResponseMessage editBread(BurgerBreadDTO changes) {
        Boolean alreadyExists = breadRepository.existsByTitleAndIdentifierNotAndDeletedFalse(changes.title(), changes.identifier());
        if (alreadyExists) return new CRUDResponseMessage(false, "Já existe um pão com esse nome!", null);
        BurgerBread breadToEdit = breadRepository.findByIdentifierAndDeletedFalse(changes.identifier());
        if (breadToEdit == null) return new CRUDResponseMessage(false, "Pão não encontrado!", null);

        List<String> changesForResponse = new ArrayList<>();

        if (!Objects.equals(breadToEdit.getTitle(), changes.title())) {
            breadToEdit.setTitle(changes.title());
            changesForResponse.add("Título modificado! Agora se chama: " + changes.title());
        }
        if (breadToEdit.getInStock() != changes.inStock()) {
            breadToEdit.setInStock(changes.inStock());
            if (changes.inStock()) changesForResponse.add(changes.title() + " disponibilizado!");
            else changesForResponse.add(changes.title() + " indisponibilizado!");
        }

        if (changesForResponse.isEmpty()) return new CRUDResponseMessage(false, "Nenhuma mudança solicitada é diferente dos dados já presentes!", null);
        breadRepository.save(breadToEdit);
        return new CRUDResponseMessage(true, null, changesForResponse);
    }
    public CRUDResponseMessage deleteBread(String breadIdentifier) {
        if (breadIdentifier == null || breadIdentifier.length() != 36) return new CRUDResponseMessage(false, "Identificador de pão inválido!", null);
        BurgerBread breadToDelete = breadRepository.findByIdentifierAndDeletedFalse(breadIdentifier);
        if (breadToDelete == null) return new CRUDResponseMessage(false, "Pão não encontrado!", null);

        breadToDelete.setDeleted(true);
        breadRepository.save(breadToDelete);
        return new CRUDResponseMessage(true, null, List.of(breadToDelete.getTitle() + " deletado!"));
    }
    public List<BurgerBreadDTO> getBreadsForMenu() {
        List<BurgerBread> breads = breadRepository.findAllByDeletedFalseAndInStockTrue();
        return breadMapper.severalToDTO(breads);
    }
}

