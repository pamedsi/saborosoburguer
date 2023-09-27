package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.burger.BurgerDTO;
import saboroso.saborosoburguer.DTOs.burger.BurgerMapper;
import saboroso.saborosoburguer.DTOs.drink.DrinkDTO;
import saboroso.saborosoburguer.DTOs.drink.DrinkMapper;
import saboroso.saborosoburguer.DTOs.portion.PortionDTO;
import saboroso.saborosoburguer.DTOs.portion.PortionMapper;
import saboroso.saborosoburguer.entities.Burger;
import saboroso.saborosoburguer.entities.Drink;
import saboroso.saborosoburguer.entities.Portion;
import saboroso.saborosoburguer.models.Menu;
import saboroso.saborosoburguer.repositories.BurgerRepository;
import saboroso.saborosoburguer.repositories.DrinkRepository;
import saboroso.saborosoburguer.repositories.PortionRepository;

import java.util.List;

@Service
public class MenuService {
    private final BurgerRepository burgerRepository;
    private final DrinkRepository drinkRepository;
    private final PortionRepository portionRepository;
    private final BurgerMapper burgerMapper;
    private final PortionMapper portionMapper;
    private final DrinkMapper drinkMapper;
    public MenuService(BurgerRepository burgerRepository, DrinkRepository drinkRepository, PortionRepository portionRepository, BurgerMapper burgerMapper, PortionMapper portionMapper, DrinkMapper drinkMapper) {
        this.burgerMapper = burgerMapper;
        this.burgerRepository = burgerRepository;
        this.drinkRepository = drinkRepository;
        this.portionRepository = portionRepository;
        this.portionMapper = portionMapper;
        this.drinkMapper = drinkMapper;
    }
    public Menu getMenuItems() {
        List<Burger> burgersPersistence = burgerRepository.findBurgerByDeletedFalseAndInStockTrue();
        List<Portion> portionsPersistence = portionRepository.findByDeletedFalseAndInStockTrue();
        List<Drink> drinksPersistence = drinkRepository.findByDeletedFalseAndInStockTrue();

        List<BurgerDTO> burgers = burgerMapper.severalToDTO(burgersPersistence);
        List<PortionDTO> portions = portionMapper.severalToDTO(portionsPersistence);
        List<DrinkDTO> drinks = drinkMapper.severalToDTO(drinksPersistence);
        return new Menu(burgers, portions, drinks);
    }
}