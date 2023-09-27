package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.addOn.AddOnDTO;
import saboroso.saborosoburguer.DTOs.burger.BurgerDTO;
import saboroso.saborosoburguer.DTOs.combo.ComboDTO;
import saboroso.saborosoburguer.DTOs.drink.DrinkDTO;
import saboroso.saborosoburguer.DTOs.portion.PortionDTO;
import saboroso.saborosoburguer.DTOs.MenuDTO;

import java.util.List;
import java.util.Map;

@Service
public class MenuService {
    private final BurgerService burgerService;
    private final PortionService portionService;
    private final ComboService comboService;
    private final AddOnService addOnService;
    private final DrinkService drinkService;

    public MenuService(BurgerService burgerService, PortionService portionService, ComboService comboService, AddOnService addOnService, DrinkService drinkService) {
        this.burgerService = burgerService;
        this.portionService = portionService;
        this.comboService = comboService;
        this.addOnService = addOnService;
        this.drinkService = drinkService;
    }

    public MenuDTO getMenuItems() {
        Map<String, List<BurgerDTO>> burgers = burgerService.getMenuBurgers();
        List<PortionDTO> portions = portionService.getPortionsForMenu();
        List<ComboDTO> combos = comboService.getCombosForMenu();
        List<AddOnDTO> addOns = addOnService.getAddOnsForMenu();
        List<DrinkDTO> drinks = drinkService.getDrinksForMenu();

        return new MenuDTO(burgers, portions, combos, drinks, addOns);
    }
}