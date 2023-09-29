package saboroso.saborosoburguer.DTO.burger;
import saboroso.saborosoburguer.utils.SoldBurgerDTO;


public record MostSoldBurgersDTO(
        SoldBurgerDTO firstBurger,
        SoldBurgerDTO secondBurger
) {}
