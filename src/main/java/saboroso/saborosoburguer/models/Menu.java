package saboroso.saborosoburguer.models;

import saboroso.saborosoburguer.DTOs.addOn.AddOnDTO;
import saboroso.saborosoburguer.DTOs.burger.MenuBurgersDTO;
import saboroso.saborosoburguer.DTOs.combo.ComboDTO;
import saboroso.saborosoburguer.DTOs.drink.DrinkDTO;
import saboroso.saborosoburguer.DTOs.portion.PortionDTO;

import java.util.List;

public record Menu(
        MenuBurgersDTO burgers,
        List<PortionDTO> portions,
        List<ComboDTO> combos,
        List<DrinkDTO> drinks,
        List<AddOnDTO> addOns
) {}
