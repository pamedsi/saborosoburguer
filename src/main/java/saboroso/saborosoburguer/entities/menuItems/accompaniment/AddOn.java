package saboroso.saborosoburguer.entities.menuItems.accompaniment;

import jakarta.persistence.*;
import lombok.*;
import saboroso.saborosoburguer.DTO.addOn.AddOnDTO;
import saboroso.saborosoburguer.entities.menuItems.MenuItem;

@Entity
@NoArgsConstructor
@Table
@Getter
@Setter
public class AddOn extends MenuItem {

    public AddOn(AddOnDTO addOnDTO) {
        setTitle(addOnDTO.title());
        setPrice(addOnDTO.price());
        setInStock(addOnDTO.inStock());
        setPic(addOnDTO.pic());
    }
}
