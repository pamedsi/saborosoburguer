package saboroso.saborosoburguer.DTOs.burger;

public record MostSoldBurgerDTO(
        BurgerForMenuDTO burger,
        Long sales
) {}
