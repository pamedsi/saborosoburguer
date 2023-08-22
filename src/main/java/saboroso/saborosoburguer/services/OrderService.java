package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTOs.order.ClientOrderDTO;
import saboroso.saborosoburguer.entities.*;
import saboroso.saborosoburguer.entities.soldItems.BurgerSale;
import saboroso.saborosoburguer.entities.soldItems.DrinkSale;
import saboroso.saborosoburguer.entities.soldItems.PortionSale;
import saboroso.saborosoburguer.repositories.*;

import java.util.List;

@Service
public class OrderService {
    private final ClientOrderRepository clientOrderRepository;
    private final UserRepository userRepository;
    private final BurgerRepository burgerRepository;
    private final PortionRepository portionRepository;
    private final DrinkRepository drinkRepository;
    private final BurgerSaleRepository burgerSaleRepository;
    private final PortionSaleRepository portionSaleRepository;
    private final DrinkSaleRepository drinkSaleRepository;

    public OrderService(ClientOrderRepository clientOrderRepository, UserRepository userRepository, BurgerRepository burgerRepository, PortionRepository portionRepository, DrinkRepository drinkRepository, BurgerSaleRepository burgerSaleRepository, PortionSaleRepository portionSaleRepository, DrinkSaleRepository drinkSaleRepository) {
        this.clientOrderRepository = clientOrderRepository;
        this.userRepository = userRepository;
        this.burgerRepository = burgerRepository;
        this.portionRepository = portionRepository;
        this.drinkRepository = drinkRepository;
        this.burgerSaleRepository = burgerSaleRepository;
        this.portionSaleRepository = portionSaleRepository;
        this.drinkSaleRepository = drinkSaleRepository;
    }
    public void makeOrder(String clientIdentifier, ClientOrderDTO orderDTO) {
        UserEntity buyer = userRepository.findByIdentifier(clientIdentifier);
        ClientOrder newOrder = new ClientOrder(buyer, orderDTO.valueToPay());
        clientOrderRepository.save(newOrder);
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
    public List<ClientOrder> getAllOrders() {
        return clientOrderRepository.findAll();
    }
}