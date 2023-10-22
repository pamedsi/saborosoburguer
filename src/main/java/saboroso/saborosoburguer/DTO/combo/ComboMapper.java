package saboroso.saborosoburguer.DTO.combo;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.DTO.order.getOrder.ComboForGetDTO;
import saboroso.saborosoburguer.entities.menuItems.accompaniment.Combo;
import saboroso.saborosoburguer.entities.soldItems.accompaniment.ComboSale;

import java.math.BigDecimal;
import java.util.List;

@Component
public class ComboMapper {

    public ComboDTO singleToDTO(Combo combo){
        return new ComboDTO(
                combo.getIdentifier(), combo.getTitle(), combo.getPrice(), combo.getPic(), combo.getInStock(), combo.getDescription()
        );
    }
    public List<ComboDTO> severalToDTO(List<Combo> combos){
        return combos.stream().map(this::singleToDTO).toList();
    }

    public ComboForGetDTO singleToComboForGetDTO(ComboSale comboSale){
        if (comboSale == null) return null;
        String title = comboSale.getSoldCombo().getTitle();
        String pic = comboSale.getSoldCombo().getPic();
        String description = comboSale.getSoldCombo().getDescription();
        BigDecimal price = comboSale.getSoldFor();
        return new ComboForGetDTO(title, pic, description, price);
    }

//    public List<ComboForGetDTO> severalToComboForGetDTO(List<ComboSale> comboSales) {
//        return comboSales.stream().map(this::singleToComboForGetDTO).toList();
//    }
}
