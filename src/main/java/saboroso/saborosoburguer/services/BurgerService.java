package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.burger.BurgerForMenuDTO;
import saboroso.saborosoburguer.DTOs.burger.BurgerMapper;
import saboroso.saborosoburguer.DTOs.burger.InputBurgerDTO;
import saboroso.saborosoburguer.DTOs.ingredient.IngredientForMenuDTO;
import saboroso.saborosoburguer.entities.Burger;
import saboroso.saborosoburguer.repositories.BurgerRepository;
import saboroso.saborosoburguer.repositories.IngredientRepository;

import java.util.List;

@Service
public class BurgerService {
    private final BurgerRepository burgerRepository;
    private final IngredientRepository ingredientRepository;
    private final BurgerMapper burgerMapper;

    private BurgerService(BurgerRepository burgerRepository, IngredientRepository ingredientRepository, BurgerMapper burgerMapper){
        this.burgerRepository = burgerRepository;
        this.ingredientRepository = ingredientRepository;
        this.burgerMapper = burgerMapper;
    }
    public Boolean createBurger (InputBurgerDTO burgerDTO) {
        if (burgerRepository.existsBurgerByTitle(burgerDTO.title())) return false;
        Burger newBurger = new Burger(burgerDTO);
        burgerRepository.save(newBurger);
        return true;
    }
//    public List<Burger> getAllBurgers () {
//        return burgerRepository.findAll();
//    }
    public List<BurgerForMenuDTO> getMenuBurgers () {
        List<Burger> burgers = burgerRepository.findBurgerByDeletedFalseAndInStockTrue();
        return burgerMapper.burgersForMenuMapper(burgers);
    }
    public Boolean addIngredientToBurger (String burgerIdentifier, String ingredientIdentifier) {
        Burger burger = burgerRepository.findBurgerByIdentifier(burgerIdentifier);
        if (burgerRepository.hasIngredient(burger, ingredientIdentifier)) return false;
        burger.getIngredients().add(ingredientRepository.findByIdentifier(ingredientIdentifier));
        burgerRepository.save(burger);
        return true;
    }
}
