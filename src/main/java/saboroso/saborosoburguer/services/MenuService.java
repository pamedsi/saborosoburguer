package saboroso.saborosoburguer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.entities.Burger;
import saboroso.saborosoburguer.entities.Drink;
import saboroso.saborosoburguer.entities.MenuItem;
import saboroso.saborosoburguer.entities.Portion;
import saboroso.saborosoburguer.model.Menu;
import saboroso.saborosoburguer.repositories.BurgerRepository;
import saboroso.saborosoburguer.repositories.DrinkRepository;
import saboroso.saborosoburguer.repositories.PortionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    private final BurgerRepository burgerRepository;
    private final DrinkRepository drinkRepository;
    private final PortionRepository portionRepository;
    public MenuService(BurgerRepository burgerRepository, DrinkRepository drinkRepository, PortionRepository portionRepository) {
        this.burgerRepository = burgerRepository;
        this.drinkRepository = drinkRepository;
        this.portionRepository = portionRepository;
    }
    public Menu getMenuItems() {
        List<Burger> burgers = burgerRepository.findBurgerByDeletedFalseAndInStockTrue();
        List<Drink> drinks = drinkRepository.findByDeletedFalseAndInStockTrue();
        List<Portion> portions = portionRepository.findByDeletedFalseAndInStockTrue();
        return new Menu(burgers, portions, drinks);
    }
}
