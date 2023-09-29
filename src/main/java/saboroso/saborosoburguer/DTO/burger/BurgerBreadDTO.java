package saboroso.saborosoburguer.DTO.burger;

public record BurgerBreadDTO(
        String identifier,
        String title,
        Boolean inStock
) {}