package saboroso.saborosoburguer.DTO.addOn;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.menuItems.accompaniment.AddOn;

import java.util.List;

@Component
public class AddOnMapper {
    public AddOnDTO singleToDTO(AddOn addOnPersistence){
        return new AddOnDTO(
                addOnPersistence.getIdentifier(),
                addOnPersistence.getTitle(),
                addOnPersistence.getPrice(),
                addOnPersistence.getPic(),
                addOnPersistence.getInStock()
        );
    }

    public List<AddOnDTO> severalToDTO(List<AddOn> addOns) {
        return addOns.stream().map(this::singleToDTO).toList();
    }
}
