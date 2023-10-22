package saboroso.saborosoburguer.entities.menuItems.accompaniment;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

import saboroso.saborosoburguer.DTO.combo.ComboDTO;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import saboroso.saborosoburguer.entities.menuItems.MenuItem;

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
