package saboroso.saborosoburguer.utils;

import saboroso.saborosoburguer.DTO.burger.BurgerDTO;

public record SoldBurgerDTO(
        BurgerDTO burger,
        Long sales
) {}
