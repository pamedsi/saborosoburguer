package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;

import saboroso.saborosoburguer.DTOs.portion.InputPortionDTO;
import saboroso.saborosoburguer.entities.Portion;
import saboroso.saborosoburguer.repositories.PortionRepository;

@Service
public class PortionService {
    private final PortionRepository portionRepository;

    public PortionService(PortionRepository portionRepository) {
        this.portionRepository = portionRepository;
    }
    public void createPortion(InputPortionDTO portionDTO) {
        Portion newPortion = new Portion(portionDTO);
        portionRepository.save(newPortion);
    }
}
