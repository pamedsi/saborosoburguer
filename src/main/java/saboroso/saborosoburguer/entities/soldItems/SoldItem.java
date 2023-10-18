package saboroso.saborosoburguer.entities.soldItems;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import saboroso.saborosoburguer.entities.CustomerOrder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class SoldItem {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private Long id;
    @ManyToOne (fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    @JoinColumn(name = "client_order_id")
    private CustomerOrder orderThatSold;
    @Column (updatable = false)
    @Setter(AccessLevel.NONE)
    private String identifier = UUID.randomUUID().toString();
    @Column (updatable = false)
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt = LocalDateTime.now();;
    @Column (updatable = false)
    private Long quantity;
    @Column (updatable = false)
    private BigDecimal singleUnitySoldFor;
}