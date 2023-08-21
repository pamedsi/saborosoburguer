package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
public class Drink extends MenuItem {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter (AccessLevel.NONE)
    @Id
    private Long id;
    @Column
    @Setter(AccessLevel.NONE)
    private String identifier = UUID.randomUUID().toString();
    @Column
    private String title = null;
    @Column
    private BigDecimal price = null;
    @Column
    private Boolean inStock = true;
    @Column
    private Boolean deleted = false;
    @Column
    private Integer mL;
}
