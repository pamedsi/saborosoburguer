package saboroso.saborosoburguer.DTO.combo;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.menuItems.accompaniment.Combo;

import java.util.List;

@Component
public class ComboMapper {

    public ComboDTO singleToDTO(Combo combo){
        return new ComboDTO(
                combo.getIdentifier(), combo.getTitle(), combo.getPrice(), combo.getPic(), combo.getInStock(), combo.getDescription()
        );
    }
    public List<ComboDTO> severalToDTO(List<Combo> combos){
        return combos.stream().map(this::singleToDTO).toList();
    }
}
