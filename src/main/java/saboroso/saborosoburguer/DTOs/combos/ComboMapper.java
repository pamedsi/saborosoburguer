package saboroso.saborosoburguer.DTOs.combos;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.DTOs.combo.ComboDTO;
import saboroso.saborosoburguer.entities.Combo;

import java.util.List;

@Component
public class ComboMapper {

    public ComboDTO singleToDTO(Combo combo){
        return new ComboDTO(
                combo.getIdentifier(), combo.getTitle(), combo.getPrice(), combo.getInStock(), combo.getDescription()
        );
    }
    public List<ComboDTO> severalToDTO(List<Combo> combos){
        return combos.stream().map(this::singleToDTO).toList();
    }
}
