package saboroso.saborosoburguer.DTO;

import saboroso.saborosoburguer.DTO.addOn.AddOnDTO;
import saboroso.saborosoburguer.DTO.burger.BurgerBreadDTO;
import saboroso.saborosoburguer.DTO.burger.BurgerDTO;
import saboroso.saborosoburguer.DTO.combo.ComboDTO;
import saboroso.saborosoburguer.DTO.drink.DrinkDTO;
import saboroso.saborosoburguer.DTO.portion.PortionDTO;

import java.util.List;
import java.util.Map;

public record MenuDTO(
        Map<String, List<BurgerDTO>> burgers,
        List<PortionDTO> portions,
        List<ComboDTO> combos,
        List<DrinkDTO> drinks,
        List<AddOnDTO> addOns,
        List<BurgerBreadDTO> breads
) {}
