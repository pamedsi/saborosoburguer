package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;

import saboroso.saborosoburguer.DTOs.portion.InputPortionDTO;
import saboroso.saborosoburguer.DTOs.portion.PortionDTO;
import saboroso.saborosoburguer.DTOs.portion.PortionMapper;
import saboroso.saborosoburguer.entities.Portion;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.repositories.PortionRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PortionService {
    private final PortionRepository portionRepository;
    private final PortionMapper portionMapper;

    public PortionService(PortionRepository portionRepository, PortionMapper portionMapper) {
        this.portionMapper = portionMapper;
        this.portionRepository = portionRepository;
    }
    public void createPortion(InputPortionDTO portionDTO) {
        Portion newPortion = new Portion(portionDTO);
        portionRepository.save(newPortion);
    }
    public List<PortionDTO> getAllPortions() {
        List<Portion> portionsPersistence = portionRepository.findAllByDeletedFalse();
        return portionMapper.severalToDTO(portionsPersistence);
    }
    public CRUDResponseMessage removePortion(String portionIdentifier) {
        if (portionIdentifier == null || portionIdentifier.length() != 36) return new CRUDResponseMessage (
                false, portionIdentifier + " não é um identificador válido!", null
        );
        Portion portion = portionRepository.findByIdentifier(portionIdentifier);
        if (portion == null) return new CRUDResponseMessage(false, "Porção não encontrada", null);
        if (portion.getDeleted()) return new CRUDResponseMessage(false, "Porção já deletada", null);

        portion.setDeleted(true);
        portionRepository.save(portion);

        return new CRUDResponseMessage(true, null, List.of(portion.getTitle() + " deletado."));
    }
    public CRUDResponseMessage editPortion(PortionDTO changes) {
        if (changes == null) return new CRUDResponseMessage(false, "Nenhuma alteração fornecida!", null);
        Boolean alreadyExists = portionRepository.existsByTitleAndIdentifierNotAndDeletedFalse(changes.title(), changes.identifier());
        if (alreadyExists) return new CRUDResponseMessage(false, "Já existe uma porção com esse nome!", null);
        Portion portionToEdit = portionRepository.findByIdentifierAndDeletedFalse(changes.identifier());
        if (portionToEdit == null) return new CRUDResponseMessage(false, "Porção não encontrada!", null);

        List<String> changesForResponse = new ArrayList<>();

        if (!Objects.equals(portionToEdit.getTitle(), changes.title())) {
            portionToEdit.setTitle(changes.title());
            changesForResponse.add("Título modificado! Agora se chama: " + changes.title());
        }
        if (!Objects.equals(portionToEdit.getDescription(), changes.description())) {
            portionToEdit.setDescription(changes.description());
            changesForResponse.add("Descrição modificado! Agora é: " + changes.title());
        }
        if (portionToEdit.getPrice().compareTo(changes.price()) != 0) {
            portionToEdit.setPrice(changes.price());
            changesForResponse.add("Preço alterado! Agora custa: " + changes.price());
        }
        if (!Objects.equals(portionToEdit.getPic(), changes.pic())) {
            portionToEdit.setPic(changes.pic());
            changesForResponse.add("Foto atualizada! Nova URL: " + changes.pic());
        }
        if (portionToEdit.getInStock() != changes.inStock()) {
            portionToEdit.setInStock(changes.inStock());
            if (changes.inStock()) changesForResponse.add(changes.title() + " disponibilizado!");
            else changesForResponse.add(changes.title() + " indisponibilizado!");
        }

        if (changesForResponse.isEmpty()) return new CRUDResponseMessage(false, "Nenhuma mudança solicitada é diferente dos dados já presentes!", null);
        portionRepository.save(portionToEdit);

        return new CRUDResponseMessage(true, null, changesForResponse);
    }
    public List<PortionDTO> getPortionsForMenu() {
        List<Portion> portionsPersistence = portionRepository.findAllByDeletedFalseAndInStockTrue();
        return portionMapper.severalToDTO(portionsPersistence);
    }
}
