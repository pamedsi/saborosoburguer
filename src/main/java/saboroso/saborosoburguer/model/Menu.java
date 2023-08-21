package saboroso.saborosoburguer.model;

import saboroso.saborosoburguer.DTOs.burger.BurgerForMenuDTO;
import saboroso.saborosoburguer.DTOs.drink.DrinkForMenuDTO;
import saboroso.saborosoburguer.DTOs.portion.PortionForMenuDTO;

import java.util.List;

public record Menu(
        List<BurgerForMenuDTO> burgers,
        List<PortionForMenuDTO> portions,
        List<DrinkForMenuDTO> drinks
) {}
