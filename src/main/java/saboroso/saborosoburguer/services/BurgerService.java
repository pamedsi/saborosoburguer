package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.burger.*;
import saboroso.saborosoburguer.entities.Burger;
import saboroso.saborosoburguer.repositories.BurgerRepository;
import saboroso.saborosoburguer.repositories.BurgerSaleRepository;
import saboroso.saborosoburguer.repositories.IngredientRepository;
import saboroso.saborosoburguer.utils.BurgersIdsAndAmounts;
import saboroso.saborosoburguer.utils.SoldBurgerDTO;

import java.util.Arrays;
import java.util.List;

@Service
public class BurgerService {
    private final BurgerRepository burgerRepository;
    private final IngredientRepository ingredientRepository;
    private final BurgerMapper burgerMapper;
    private final BurgerSaleRepository burgerSaleRepository;
    private BurgerService(BurgerRepository burgerRepository, IngredientRepository ingredientRepository, BurgerMapper burgerMapper, BurgerSaleRepository burgerSaleRepository){
        this.burgerRepository = burgerRepository;
        this.ingredientRepository = ingredientRepository;
        this.burgerMapper = burgerMapper;
        this.burgerSaleRepository = burgerSaleRepository;
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
    public List<BurgerManagementDTO> getBurgersForMenuManagement() {
        List<Burger> burgers = burgerRepository.findAll();
        return burgerMapper.burgersForManagementMapper(burgers);
    }
    public MostSoldBurgersDTO getHighLightBurgers() {
        try {
            BurgersIdsAndAmounts burgersIdsAndAmounts = new BurgersIdsAndAmounts(burgerSaleRepository.getMostSold());
            Burger burger1Persistence = burgerRepository.findSingleById(burgersIdsAndAmounts.firstBurgerId);
            Burger burger2Persistence = burgerRepository.findSingleById(burgersIdsAndAmounts.secondBurgerId);
            List<BurgerForMenuDTO> burgersDTO = burgerMapper.burgersForMenuMapper(Arrays.asList(burger1Persistence, burger2Persistence));
            SoldBurgerDTO burger1 = new SoldBurgerDTO(burgersDTO.get(0), burgersIdsAndAmounts.firstBurgerAmount);
            SoldBurgerDTO burger2 = new SoldBurgerDTO(burgersDTO.get(1), burgersIdsAndAmounts.secondBurgerAmount);
            return new MostSoldBurgersDTO(burger1, burger2);
        }
        catch (Exception e) {
            return null;
        }
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
