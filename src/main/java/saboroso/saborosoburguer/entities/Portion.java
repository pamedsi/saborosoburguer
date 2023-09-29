package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.*;
import saboroso.saborosoburguer.DTO.portion.InputPortionDTO;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table
public class Portion {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter (AccessLevel.NONE)
    @Id
    private Long id;
    @Column
    @Setter(AccessLevel.NONE)
    private String identifier = UUID.randomUUID().toString();
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
    @Column (length = 2000)
    private String pic;

    public Portion (InputPortionDTO portionDTO) {
        title = portionDTO.title();
        price = portionDTO.price();
        inStock = portionDTO.inStock();
        deleted = false;
        description = portionDTO.description();
        pic = portionDTO.pic();
    }
}