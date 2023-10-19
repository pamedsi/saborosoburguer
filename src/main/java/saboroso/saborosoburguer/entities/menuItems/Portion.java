package saboroso.saborosoburguer.entities.menuItems;

import jakarta.persistence.*;
import lombok.*;
import saboroso.saborosoburguer.DTO.portion.PortionDTO;

@Entity
@NoArgsConstructor
@Table
@Getter
@Setter
public class Portion extends MenuItem {
    @Column
    @Setter
    private String description;

    public Portion(PortionDTO portionDTO) {
        setTitle(portionDTO.title());
        setPrice(portionDTO.price());
        setInStock(portionDTO.inStock());
        setPic(portionDTO.pic());
        setDescription(portionDTO.description());
    }
}
