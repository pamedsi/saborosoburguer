package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;

import saboroso.saborosoburguer.DTOs.portion.InputPortionDTO;
import saboroso.saborosoburguer.DTOs.portion.PortionDTO;
import saboroso.saborosoburguer.DTOs.portion.PortionMapper;
import saboroso.saborosoburguer.entities.Portion;
import saboroso.saborosoburguer.models.CRUDResponseMessage;
import saboroso.saborosoburguer.repositories.PortionRepository;

import java.util.List;

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
}
