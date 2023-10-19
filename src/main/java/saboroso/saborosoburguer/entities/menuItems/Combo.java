package saboroso.saborosoburguer.entities.menuItems;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

import saboroso.saborosoburguer.DTO.combo.ComboDTO;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table
@Getter
@Setter
public class Combo extends MenuItem {
    @Column
    @Setter
    private String description;

    public Combo(ComboDTO comboDTO) {
        setTitle(comboDTO.title());
        setPrice(comboDTO.price());
        setInStock(comboDTO.inStock());
        setPic(comboDTO.pic());
        setDescription(comboDTO.description());
    }
}
