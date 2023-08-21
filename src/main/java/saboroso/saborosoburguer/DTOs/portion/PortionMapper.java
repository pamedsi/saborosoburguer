package saboroso.saborosoburguer.DTOs.portion;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.Portion;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PortionMapper {
    public PortionForMenuDTO singlePortionToMenuDTO(Portion portion) {
        return new PortionForMenuDTO(portion.getIdentifier(), portion.getTitle(), portion.getPrice(), portion.getDescription());
    }
    public List<PortionForMenuDTO> portionForMenuDTOMapper (List<Portion> portions){
        return portions.stream().map(this::singlePortionToMenuDTO).collect(Collectors.toList());
    }
}