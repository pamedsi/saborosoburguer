package saboroso.saborosoburguer.models;

import java.util.List;

public record BurgerResponseMessage (
        Boolean worked,
        String reasonWhy,
        List<String> changes
){}