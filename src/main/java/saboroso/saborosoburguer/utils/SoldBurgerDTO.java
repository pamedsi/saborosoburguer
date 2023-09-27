package saboroso.saborosoburguer.utils;

import saboroso.saborosoburguer.DTOs.burger.BurgerDTO;

public record SoldBurgerDTO(
        BurgerDTO burger,
        Long sales
) {}
