package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import saboroso.saborosoburguer.DTOs.addOn.AddOnDTO;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table
@Data
@NoArgsConstructor
public class AddOn {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Setter (AccessLevel.NONE)
    private Long id;
    @Column (unique = true)
    @Setter (AccessLevel.NONE)
    private String identifier;
    @Column (unique = true)
    private String title;
    @Column
    private BigDecimal price;
    @Column
    private Boolean inStock;
    @Column
    private Boolean deleted;

    public AddOn(AddOnDTO addOnDTO) {
        identifier = UUID.randomUUID().toString();
        title = addOnDTO.title();
        price = addOnDTO.price();
        inStock = addOnDTO.inStock();
        deleted = false;
    }
}
