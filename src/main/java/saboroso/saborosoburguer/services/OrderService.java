package saboroso.saborosoburguer.services;

import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTO.order.BurgerFromOrder;
import saboroso.saborosoburguer.DTO.order.OrderDTO;
import saboroso.saborosoburguer.DTO.order.PortionFromOrder;
import saboroso.saborosoburguer.entities.*;
import saboroso.saborosoburguer.entities.menuItems.*;
import saboroso.saborosoburguer.entities.menuItems.burger.Burger;
import saboroso.saborosoburguer.entities.menuItems.burger.BurgerBread;
import saboroso.saborosoburguer.entities.soldItems.*;
import saboroso.saborosoburguer.models.UserAndAddress;
import saboroso.saborosoburguer.repositories.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    // Repositório do pedido
    private final CostumerOrderRepository customerOrderRepository;

    // Comidas e bebidas do pedido
    private final BurgerRepository burgerRepository;
    private final PortionRepository portionRepository;
    private final AddOnRepository addOnRepository;
    private final ComboRepository comboRepository;
    private final BreadRepository breadRepository;
    private final DrinkRepository drinkRepository;

    // Vendas

    private final AddOnSaleRepository addOnSaleRepository;
    private final BurgerSaleRepository burgerSaleRepository;
    private final PortionSaleRepository portionSaleRepository;
    private final DrinkSaleRepository drinkSaleRepository;
    private final ComboSaleRepository comboSaleRepository;

    // Cliente

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final UserService userService;


    public OrderService(CostumerOrderRepository costumerOrderRepository, UserRepository userRepository, BurgerRepository burgerRepository, PortionRepository portionRepository, DrinkRepository drinkRepository, BurgerSaleRepository burgerSaleRepository, PortionSaleRepository portionSaleRepository, DrinkSaleRepository drinkSaleRepository, AddressRepository addressRepository, AddOnRepository addOnRepository, AddOnSaleRepository addOnSaleRepository, ComboRepository comboRepository, BreadRepository breadRepository, UserService userService, ComboSaleRepository comboSaleRepository) {
        this.customerOrderRepository = costumerOrderRepository;
        this.userRepository = userRepository;
        this.burgerRepository = burgerRepository;
        this.portionRepository = portionRepository;
        this.drinkRepository = drinkRepository;
        this.burgerSaleRepository = burgerSaleRepository;
        this.portionSaleRepository = portionSaleRepository;
        this.drinkSaleRepository = drinkSaleRepository;
        this.addressRepository = addressRepository;
        this.addOnRepository = addOnRepository;
        this.addOnSaleRepository = addOnSaleRepository;
        this.comboRepository = comboRepository;
        this.breadRepository = breadRepository;
        this.userService = userService;
        this.comboSaleRepository = comboSaleRepository;
    }
    public void makeOrder(OrderDTO orderDTO) {
        UserEntity buyer = userRepository.findByPhoneNumber(orderDTO.clientPhoneNumber());
        Address addressToDeliver = null;
        if (buyer == null) {
            UserAndAddress userAndAddress = userService.addClientUser(orderDTO.clientName(), orderDTO.clientPhoneNumber(), orderDTO.addressToDeliver());
            buyer = userAndAddress.user();
            addressToDeliver = userAndAddress.address();
        }
        if (addressToDeliver == null) {
            addressToDeliver = addressRepository.findByContentAndBelongsTo(orderDTO.addressToDeliver(), buyer);
        }
        if (addressToDeliver == null) {
            addressToDeliver = userService.addAddressToClient(buyer, orderDTO.addressToDeliver());
        }

        CustomerOrder newOrder = new CustomerOrder(orderDTO.orderCode(), orderDTO.status(), buyer, addressToDeliver, orderDTO.totalToPay(), orderDTO.paymentMethod(), orderDTO.howClientWillPay());

        List<BurgerSale> soldBurgers = new ArrayList<>();
        List<PortionSale> soldPortions = new ArrayList<>();
        List<DrinkSale> soldDrinks = new ArrayList<>();
        List<AddOnSale> soldAddOns = new ArrayList<>();
        List<ComboSale> soldCombos = new ArrayList<>();

        orderDTO.burgers().forEach(purchasedBurger -> {
            countBurgers(newOrder, soldBurgers, purchasedBurger, soldAddOns, soldCombos);
        });

        orderDTO.portions().forEach(purchasedPortion -> {
            countPortions(newOrder, soldPortions, purchasedPortion, soldAddOns);
        });

        orderDTO.drinks().forEach(purchasedDrink -> {
            Drink soldDrink = drinkRepository.findByIdentifierAndDeletedFalseAndInStockTrue(purchasedDrink.drinkIdentifier());
            if (soldDrink == null) throw new NotFoundException("Bebida não disponível ou em deletada.");
            soldDrinks.add(new DrinkSale(newOrder, soldDrink, purchasedDrink.quantity()));
        });

        if (!soldBurgers.isEmpty()) burgerSaleRepository.saveAll(soldBurgers);
        if (!soldPortions.isEmpty()) portionSaleRepository.saveAll(soldPortions);
        if (!soldDrinks.isEmpty()) drinkSaleRepository.saveAll(soldDrinks);
        if (!soldAddOns.isEmpty()) addOnSaleRepository.saveAll(soldAddOns);
        if (!soldCombos.isEmpty()) comboSaleRepository.saveAll(soldCombos);
        customerOrderRepository.save(newOrder);
    }
    public List<CustomerOrder> getAllOrders() {
        return customerOrderRepository.findAll();
    }

    private void countBurgers(CustomerOrder order, List<BurgerSale> soldBurgers, BurgerFromOrder purchasedItem, List<AddOnSale> addOns, List<ComboSale> combos) {
        Burger burger = burgerRepository.findBurgerByIdentifierAndDeletedFalseAndInStockTrue(purchasedItem.identifier());

        if (burger == null) throw new NotFoundException("Hambúrguer em falta ou deletado.");

        int index = findIndex(soldBurgers, purchasedItem.identifier());
        if (index == -1) {
            BurgerBread bread = breadRepository.findByIdentifierAndDeletedFalseAndInStockTrue(purchasedItem.breadIdentifier());
            if (bread == null) throw new NotFoundException("Pão em falta ou deletado.");

            soldBurgers.add(new BurgerSale(order, burger, bread));
                if (purchasedItem.comboIdentifier() != null) {
                    Combo combo = comboRepository.findByIdentifierAndDeletedFalseAndInStockTrue(purchasedItem.comboIdentifier());
                    if (combo == null) throw new NotFoundException("Combo não encontrado ou deletado.");
                    combos.add(new ComboSale(order, combo));
                }
        }
        else soldBurgers.get(index).incrementQuantity();

        countAddOns(order, addOns, purchasedItem.addOnsIdentifiers());
    }

    private void countPortions(CustomerOrder order, List<PortionSale> soldPortions, PortionFromOrder purchasedItem, List<AddOnSale> addOns) {
        Portion portion = portionRepository.findByIdentifierAndDeletedFalseAndInStockTrue(purchasedItem.identifier());
        if (portion == null) throw new NotFoundException("Porção em falta ou deletada.");

        int index = findIndex(soldPortions, purchasedItem.identifier());
        if (index == -1) soldPortions.add(new PortionSale(order, portion));
        else soldPortions.get(index).incrementQuantity();
        countAddOns(order, addOns, purchasedItem.addOnsIdentifiers());
    }

    private void countAddOns(CustomerOrder order, List<AddOnSale> addOns, List<String> addOnsIdentifiers){
        addOnsIdentifiers.forEach(addOnIdentifier -> {
            AddOn addOn = addOnRepository.findByIdentifierAndDeletedFalseAndInStockTrue(addOnIdentifier);
            if (addOn == null) throw new NotFoundException("Adicional não encontrado ou deletado.");

            int i = findIndex(addOns, addOnIdentifier);
            if (i == -1) addOns.add(new AddOnSale(order, addOn));
            else addOns.get(i).incrementQuantity();
        });
    }

    private <T extends SoldItem> int findIndex(List<T> soldItems, String itemToCheck){
        for (int i = 0; i < soldItems.size(); i++) {
            if (soldItems.get(i).getIdentifier().equals(itemToCheck)) {
                return i;
            }
        }
        return -1;
    }

}