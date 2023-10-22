package saboroso.saborosoburguer.DTO.portion;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.DTO.addOn.AddOnMapper;
import saboroso.saborosoburguer.DTO.order.getOrder.AddOnForGetOrderDTO;
import saboroso.saborosoburguer.DTO.order.getOrder.PortionForGetOrderDTO;
import saboroso.saborosoburguer.entities.menuItems.Portion;
import saboroso.saborosoburguer.entities.soldItems.PortionSale;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class PortionMapper {
    private final AddOnMapper addOnMapper;

    public PortionMapper(AddOnMapper addOnMapper) {
        this.addOnMapper = addOnMapper;
    }


    public PortionDTO singlePortionToDTO(Portion portion) {
        return new PortionDTO(
                portion.getIdentifier(),
                portion.getTitle(),
                portion.getPrice(),
                portion.getDescription(),
                portion.getPic(),
                portion.getInStock()
                );
    }
    public List<PortionDTO> severalToDTO (List<Portion> portions){
        return portions.stream().map(this::singlePortionToDTO).collect(Collectors.toList());
    }

    public PortionForGetOrderDTO singleToGetOrderDTO(PortionSale portionSale){
        String title = portionSale.getSoldPortion().getTitle();
        String pic = portionSale.getSoldPortion().getPic();
        List<AddOnForGetOrderDTO> addOns = new ArrayList<>();
        if (!portionSale.getAddOns().isEmpty()) addOns = addOnMapper.severalFromAddOnSaleToAddOnForGetOrderDTO(portionSale.getAddOns());
        String obs = portionSale.getObs();
        BigDecimal price = portionSale.getSoldFor();
        return new PortionForGetOrderDTO(title, pic, addOns, obs, price);
    }

    public List<PortionForGetOrderDTO> severalToGetOrderDTO(List<PortionSale> portionSales){
        return portionSales.stream().map(this::singleToGetOrderDTO).toList();
    }
}