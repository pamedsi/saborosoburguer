package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

import saboroso.saborosoburguer.DTO.combo.ComboDTO;

@Entity
@Data
@NoArgsConstructor
@Table
public class Combo {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter (AccessLevel.NONE)
    @Id
    private Long id;
    @Column
    @Setter(AccessLevel.NONE)
    private String identifier;
    @Column (unique = true)
    private String title;
    @Column
    private BigDecimal price;
    @Column
    private Boolean inStock;
    @Column
    private Boolean deleted;
    @Column (unique = true)
    private String description;

    public Combo (ComboDTO comboDTO) {
        identifier = UUID.randomUUID().toString();
        title = comboDTO.title();
        price = comboDTO.price();
        inStock = comboDTO.inStock();
        deleted = false;
        description = comboDTO.description();
    }
}