package saboroso.saborosoburguer.DTO.burger;

import org.springframework.stereotype.Component;
import saboroso.saborosoburguer.entities.burger.BurgerBread;

import java.util.List;

@Component
public class BreadMapper {
    public BurgerBreadDTO singleToDTO(BurgerBread breadPersistence) {
        return new BurgerBreadDTO(
                breadPersistence.getIdentifier(),
                breadPersistence.getTitle(),
                breadPersistence.getInStock()
        );
    }
    public List<BurgerBreadDTO> severalToDTO(List<BurgerBread> breadsPersistence) {
        return breadsPersistence.stream().map(this::singleToDTO).toList();
    }
}
