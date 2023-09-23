package saboroso.saborosoburguer.DTOs.addOn;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.AddOn;

import java.util.List;

@Component
public class AddOnMapper {
    public AddOnDTO singleToDTO(AddOn addOnPersistence){
        return new AddOnDTO(
                addOnPersistence.getIdentifier(),
                addOnPersistence.getTitle(),
                addOnPersistence.getPrice(),
                addOnPersistence.getInStock()
        );
    }

    public List<AddOnDTO> severalToDTO(List<AddOn> addOns) {
        return addOns.stream().map(this::singleToDTO).toList();
    }
}
