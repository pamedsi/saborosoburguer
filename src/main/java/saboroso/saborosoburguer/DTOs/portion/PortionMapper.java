package saboroso.saborosoburguer.DTOs.portion;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.Portion;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PortionMapper {
    public PortionDTO singlePortionToDTO(Portion portion) {
        return new PortionDTO(
                portion.getIdentifier(),
                portion.getTitle(),
                portion.getPrice(),
                portion.getDescription(),
                portion.getInStock()
                );
    }
    public List<PortionDTO> severalToDTO (List<Portion> portions){
        return portions.stream().map(this::singlePortionToDTO).collect(Collectors.toList());
    }

}