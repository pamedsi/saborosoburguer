package saboroso.saborosoburguer.DTO.burger;

import org.springframework.stereotype.Component;

import saboroso.saborosoburguer.DTO.addOn.AddOnMapper;
import saboroso.saborosoburguer.DTO.category.CategoryDTO;
import saboroso.saborosoburguer.DTO.combo.ComboMapper;
import saboroso.saborosoburguer.DTO.ingredient.IngredientMapper;
import saboroso.saborosoburguer.DTO.order.getOrder.AddOnForGetOrderDTO;
import saboroso.saborosoburguer.DTO.order.getOrder.BreadForGetOrderDTO;
import saboroso.saborosoburguer.DTO.order.getOrder.BurgerForGetOrderDTO;
import saboroso.saborosoburguer.DTO.order.getOrder.ComboForGetDTO;
import saboroso.saborosoburguer.entities.menuItems.burger.Burger;
import saboroso.saborosoburguer.entities.soldItems.BurgerSale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BurgerMapper {
    private final IngredientMapper ingredientMapper;
    private final AddOnMapper addOnMapper;
    private final ComboMapper comboMapper;
    public BurgerMapper(IngredientMapper ingredientMapper, AddOnMapper addOnMapper, ComboMapper comboMapper) {
        this.ingredientMapper = ingredientMapper;
        this.addOnMapper = addOnMapper;
        this.comboMapper = comboMapper;
    }

    public BurgerDTO singleToDTO(Burger persistenceBurger) {
                return new BurgerDTO(
                persistenceBurger.getIdentifier(),
                    new CategoryDTO(
                        persistenceBurger.getBurgerCategory().getIdentifier(),
                        persistenceBurger.getBurgerCategory().getTitle()
                    ),
                    persistenceBurger.getTitle(),
                    persistenceBurger.getPrice(),
                    persistenceBurger.getPic(),
                    persistenceBurger.getInStock(),
                    ingredientMapper.severalToDTO(persistenceBurger.getIngredients()
                    ));
    }
    public List<BurgerDTO> severalToDTO(List<Burger> burgers) {
        return burgers.stream()
                .map(this::singleToDTO)
                .collect(Collectors.toList());
    }

    public BurgerForGetOrderDTO fromBurgerSaleToBurgerForGetOrderDTO(BurgerSale burgerSale){
        String title = burgerSale.getSoldburger().getTitle();
        List<AddOnForGetOrderDTO> addOns = new ArrayList<>();
        if (!burgerSale.getAddOns().isEmpty()) addOns = addOnMapper.severalFromAddOnSaleToAddOnForGetOrderDTO(burgerSale.getAddOns());
        BreadForGetOrderDTO bread = new BreadForGetOrderDTO(burgerSale.getSoldburger().getTitle(), burgerSale.getBread().getPic());
        ComboForGetDTO combo = comboMapper.singleToComboForGetDTO(burgerSale.getCombo());
        String obs = burgerSale.getObs();
        BigDecimal itemSoldBy = burgerSale.getSoldFor();

        return new BurgerForGetOrderDTO(title, addOns, bread, combo, obs, itemSoldBy);
    }

    public List<BurgerForGetOrderDTO> severalToGetOrderDTO(List<BurgerSale> burgerSales) {
        return burgerSales.stream().map(this::fromBurgerSaleToBurgerForGetOrderDTO).toList();
    }

}