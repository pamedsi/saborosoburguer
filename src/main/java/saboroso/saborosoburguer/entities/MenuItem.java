package saboroso.saborosoburguer.entities;



import jakarta.persistence.*;
import lombok.AccessLevel;

import lombok.Data;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class MenuItem {
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
}