package saboroso.saborosoburguer.DTO.drink;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.DTO.order.getOrder.DrinkAndQuantityForGetDTO;
import saboroso.saborosoburguer.entities.menuItems.Drink;
import saboroso.saborosoburguer.entities.soldItems.DrinkSale;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DrinkMapper {
    public DrinkDTO singleToDTO (Drink drink){
        return new DrinkDTO(drink.getIdentifier(), drink.getTitle(), drink.getMl(), drink.getPrice(), drink.getPic(), drink.getInStock());
    }
    public List<DrinkDTO> severalToDTO(List<Drink> drinks){
        return drinks.stream().map(this::singleToDTO).collect(Collectors.toList());
    };

    public DrinkAndQuantityForGetDTO singleToGetDTO(DrinkSale drinkSale) {
        String title = drinkSale.getSoldDrink().getTitle();
        Integer ml = drinkSale.getSoldDrink().getMl();
        BigDecimal price = drinkSale.getSingleUnitySoldFor();
        Integer quantity = drinkSale.getQuantity();

        return new DrinkAndQuantityForGetDTO(title, ml, price, quantity);
    }

    public List<DrinkAndQuantityForGetDTO> severalToGetOrderDTO(List<DrinkSale> drinkSales){
        return drinkSales.stream().map(this::singleToGetDTO).toList();
    }

}
