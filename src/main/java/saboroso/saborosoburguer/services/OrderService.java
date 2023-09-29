package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTO.order.OrderDTO;
import saboroso.saborosoburguer.entities.*;
import saboroso.saborosoburguer.entities.burger.Burger;
import saboroso.saborosoburguer.entities.soldItems.BurgerSale;
import saboroso.saborosoburguer.entities.soldItems.DrinkSale;
import saboroso.saborosoburguer.entities.soldItems.PortionSale;
import saboroso.saborosoburguer.repositories.*;

import java.util.List;

@Service
public class OrderService {
    private final CostumerOrderRepository costumerOrderRepository;
    private final UserRepository userRepository;
    private final BurgerRepository burgerRepository;
    private final PortionRepository portionRepository;
    private final DrinkRepository drinkRepository;
    private final BurgerSaleRepository burgerSaleRepository;
    private final PortionSaleRepository portionSaleRepository;
    private final DrinkSaleRepository drinkSaleRepository;

    public OrderService(CostumerOrderRepository costumerOrderRepository, UserRepository userRepository, BurgerRepository burgerRepository, PortionRepository portionRepository, DrinkRepository drinkRepository, BurgerSaleRepository burgerSaleRepository, PortionSaleRepository portionSaleRepository, DrinkSaleRepository drinkSaleRepository) {
        this.costumerOrderRepository = costumerOrderRepository;
        this.userRepository = userRepository;
        this.burgerRepository = burgerRepository;
        this.portionRepository = portionRepository;
        this.drinkRepository = drinkRepository;
        this.burgerSaleRepository = burgerSaleRepository;
        this.portionSaleRepository = portionSaleRepository;
        this.drinkSaleRepository = drinkSaleRepository;
    }
    public void makeOrder(String clientIdentifier, OrderDTO orderDTO) {
        UserEntity buyer = userRepository.findByIdentifier(clientIdentifier);
        CustomerOrder newOrder = new CustomerOrder(buyer, orderDTO.valueToPay(), orderDTO.Address());
        costumerOrderRepository.save(newOrder);
        orderDTO.burgers().forEach(burger -> {
            Burger burgerPersistence = burgerRepository.findBurgerByIdentifier(burger.identifier());
            burgerSaleRepository.save(new BurgerSale(newOrder, burgerPersistence, burger.quantity()));
        });
        orderDTO.portions().forEach(portion -> {
            Portion portionPersistence = portionRepository.findByIdentifier(portion.identifier());
            portionSaleRepository.save(new PortionSale(newOrder, portionPersistence, portion.quantity()));
        });
        orderDTO.drinks().forEach(drink -> {
            Drink drinkPersistence = drinkRepository.findByIdentifier(drink.identifier());
            drinkSaleRepository.save(new DrinkSale(newOrder, drinkPersistence, drink.quantity()));
        });
    }
    public List<CustomerOrder> getAllOrders() {
        return costumerOrderRepository.findAll();
    }
}