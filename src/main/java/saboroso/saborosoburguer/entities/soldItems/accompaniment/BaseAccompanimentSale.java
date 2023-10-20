package saboroso.saborosoburguer.entities.soldItems.accompaniment;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class BaseAccompanimentSale {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Long id;
    @Column (updatable = false)
    @Setter(AccessLevel.NONE)
    private String identifier = UUID.randomUUID().toString();
    @Column (updatable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime soldAt = LocalDateTime.now();;
    @Column (updatable = false)
    private BigDecimal soldFor;
}