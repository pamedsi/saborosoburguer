package saboroso.saborosoburguer.entities.menuItems;

import jakarta.persistence.*;
import lombok.*;
import saboroso.saborosoburguer.DTO.addOn.AddOnDTO;

@Entity
@Table
@NoArgsConstructor
public class AddOn extends MenuItem{

    public AddOn(AddOnDTO addOnDTO) {
        setTitle(addOnDTO.title());
        setPrice(addOnDTO.price());
        setInStock(addOnDTO.inStock());
        setPic(addOnDTO.pic());
    }
}
