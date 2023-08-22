package saboroso.saborosoburguer.utils;

import saboroso.saborosoburguer.DTOs.burger.BurgerForMenuDTO;

public record SoldBurgerDTO(
        BurgerForMenuDTO burger,
        Long sales
) {}
