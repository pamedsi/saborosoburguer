package saboroso.saborosoburguer.DTO.addOn;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.DTO.order.getOrder.AddOnForGetOrderDTO;
import saboroso.saborosoburguer.entities.menuItems.accompaniment.AddOn;
import saboroso.saborosoburguer.entities.soldItems.accompaniment.AddOnSale;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AddOnMapper {
    public AddOnDTO singleToDTO(AddOn addOnPersistence){
        return new AddOnDTO(
                addOnPersistence.getIdentifier(),
                addOnPersistence.getTitle(),
                addOnPersistence.getPrice(),
                addOnPersistence.getPic(),
                addOnPersistence.getInStock()
        );
    }

    public List<AddOnDTO> severalToDTO(List<AddOn> addOns) {
        return addOns.stream().map(this::singleToDTO).toList();
    }

    public AddOnForGetOrderDTO singleFromAddOnSaleToAddOnForGetOrderDTO(AddOnSale addOnSale){
        String title = addOnSale.getSoldAddOn().getTitle();
        String pic = addOnSale.getSoldAddOn().getPic();
        BigDecimal price = addOnSale.getSoldFor();
        return new AddOnForGetOrderDTO(title, pic, price);
    }

    public List<AddOnForGetOrderDTO> severalFromAddOnSaleToAddOnForGetOrderDTO (List<AddOnSale> addOnSalesList){
        return addOnSalesList.stream().map(this::singleFromAddOnSaleToAddOnForGetOrderDTO).toList()        ;
    }
}
