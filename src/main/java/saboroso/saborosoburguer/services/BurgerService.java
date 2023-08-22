package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.burger.BurgerForMenuDTO;
import saboroso.saborosoburguer.DTOs.burger.BurgerMapper;
import saboroso.saborosoburguer.DTOs.burger.InputBurgerDTO;
import saboroso.saborosoburguer.entities.Burger;
import saboroso.saborosoburguer.entities.ClientOrder;
import saboroso.saborosoburguer.repositories.BurgerRepository;
import saboroso.saborosoburguer.repositories.ClientOrderRepository;
import saboroso.saborosoburguer.repositories.IngredientRepository;

import java.util.List;

@Service
public class BurgerService {
    private final BurgerRepository burgerRepository;
    private final IngredientRepository ingredientRepository;
    private final BurgerMapper burgerMapper;
    private final ClientOrderRepository clientOrderRepository;
    private BurgerService(BurgerRepository burgerRepository, IngredientRepository ingredientRepository, BurgerMapper burgerMapper, ClientOrderRepository clientOrderRepository){
        this.burgerRepository = burgerRepository;
        this.ingredientRepository = ingredientRepository;
        this.burgerMapper = burgerMapper;
        this.clientOrderRepository = clientOrderRepository;
    }
    public Boolean createBurger (InputBurgerDTO burgerDTO) {
        if (burgerRepository.existsBurgerByTitle(burgerDTO.title())) return false;
        Burger newBurger = new Burger(burgerDTO);
        burgerRepository.save(newBurger);
        return true;
    }
    public List<BurgerForMenuDTO> getMenuBurgers () {
        List<Burger> burgers = burgerRepository.findBurgerByDeletedFalseAndInStockTrue();
        return burgerMapper.burgersForMenuMapper(burgers);
    }
    public List<BurgerForMenuDTO> getHighLightBurgers() {
        return null;
    }
    public Boolean addIngredientToBurger (String burgerIdentifier, String ingredientIdentifier) {
        Burger burger = burgerRepository.findBurgerByIdentifier(burgerIdentifier);
        if (burgerRepository.hasIngredient(burger, ingredientIdentifier)) return false;
        burger.getIngredients().add(ingredientRepository.findByIdentifier(ingredientIdentifier));
        burgerRepository.save(burger);
        return true;
    }
    public Boolean removeIngredient(String burgerIdentifier, String ingredientIdentifier) {
        Burger burger = burgerRepository.findBurgerByIdentifier(burgerIdentifier);
        if (!burgerRepository.hasIngredient(burger, ingredientIdentifier)) return false;
        burger.getIngredients().remove(ingredientRepository.findByIdentifier(ingredientIdentifier));
        burgerRepository.save(burger);
        return true;
    }
    public Boolean addPhotoToBurger(String burgerIdentifier, String pic) {
        Burger burger = burgerRepository.findBurgerByIdentifier(burgerIdentifier);
        if (pic.isBlank()) return false;
        else if (burger.getPic() != null && burger.getPic().equals(pic)) return false;
        burger.setPic(pic);
        burgerRepository.save(burger);
        return true;
    }
}
