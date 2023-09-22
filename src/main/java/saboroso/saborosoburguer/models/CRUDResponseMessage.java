package saboroso.saborosoburguer.models;

import java.util.List;

public record CRUDResponseMessage(
        Boolean worked,
        String reasonWhy,
        List<String> changes
){}