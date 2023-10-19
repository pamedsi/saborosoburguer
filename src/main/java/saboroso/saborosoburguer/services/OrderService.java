package saboroso.saborosoburguer.services;

import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTO.order.OrderDTO;
import saboroso.saborosoburguer.entities.*;
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
    public void makeOrder(OrderDTO orderDTO) {
//        UserEntity buyer = userRepository.findByIdentifier();

    }
    public List<CustomerOrder> getAllOrders() {
        return costumerOrderRepository.findAll();
    }
}