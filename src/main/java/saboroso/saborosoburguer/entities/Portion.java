package saboroso.saborosoburguer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Portion {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter (AccessLevel.NONE)
    @Id
    private Long id;
    @Column
    @Setter(AccessLevel.NONE)
    String identifier = UUID.randomUUID().toString();
    @Column
    String title = null;
    @Column
    BigDecimal price = null;
    @Column
    Boolean inStock = true;
    @Column
    Boolean deleted = false;
    @Column
    private String description;
}