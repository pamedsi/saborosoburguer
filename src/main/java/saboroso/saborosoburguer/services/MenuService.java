package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTO.addOn.AddOnDTO;
import saboroso.saborosoburguer.DTO.burger.BurgerBreadDTO;
import saboroso.saborosoburguer.DTO.burger.BurgerDTO;
import saboroso.saborosoburguer.DTO.combo.ComboDTO;
import saboroso.saborosoburguer.DTO.drink.DrinkDTO;
import saboroso.saborosoburguer.DTO.portion.PortionDTO;
import saboroso.saborosoburguer.DTO.MenuDTO;

import java.util.List;
import java.util.Map;

@Service
public class MenuService {
    private final BurgerService burgerService;
    private final PortionService portionService;
    private final ComboService comboService;
    private final AddOnService addOnService;
    private final DrinkService drinkService;
    private final BreadService breadService;

    public MenuService(BurgerService burgerService, PortionService portionService, ComboService comboService, AddOnService addOnService, DrinkService drinkService, BreadService breadService) {
        this.burgerService = burgerService;
        this.portionService = portionService;
        this.comboService = comboService;
        this.addOnService = addOnService;
        this.drinkService = drinkService;
        this.breadService = breadService;
    }

    public MenuDTO getMenuItems() {
        Map<String, List<BurgerDTO>> burgers = burgerService.getMenuBurgers();
        List<PortionDTO> portions = portionService.getPortionsForMenu();
        List<DrinkDTO> drinks = drinkService.getDrinksForMenu();

        // Dependentes de hambúrguer e porções
        List<BurgerBreadDTO> breads = breadService.getBreadsForMenu();
        List<ComboDTO> combos = comboService.getCombosForMenu();
        List<AddOnDTO> addOns = addOnService.getAddOnsForMenu();

        return new MenuDTO(burgers, portions, combos, drinks, addOns, breads);
    }
}