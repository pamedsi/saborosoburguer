package saboroso.saborosoburguer.models;

import saboroso.saborosoburguer.DTOs.burger.BurgerForMenuDTO;
import saboroso.saborosoburguer.DTOs.drink.DrinkDTO;
import saboroso.saborosoburguer.DTOs.portion.PortionDTO;

import java.util.List;

public record Menu(
        List<BurgerForMenuDTO> burgers,
        List<PortionDTO> portions,
        List<DrinkDTO> drinks
) {}