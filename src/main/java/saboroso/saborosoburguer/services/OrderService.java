package saboroso.saborosoburguer.services;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.springframework.stereotype.Service;
import saboroso.saborosoburguer.DTO.burger.BurgerMapper;
import saboroso.saborosoburguer.DTO.drink.DrinkMapper;
import saboroso.saborosoburguer.DTO.order.getOrder.BurgerForGetOrderDTO;
import saboroso.saborosoburguer.DTO.order.getOrder.DrinkAndQuantityForGetDTO;
import saboroso.saborosoburguer.DTO.order.getOrder.OrderForGetDTO;
import saboroso.saborosoburguer.DTO.order.getOrder.PortionForGetOrderDTO;
import saboroso.saborosoburguer.DTO.order.postOrder.OrderForPostDTO;
import saboroso.saborosoburguer.DTO.portion.PortionMapper;
import saboroso.saborosoburguer.entities.*;
import saboroso.saborosoburguer.entities.menuItems.*;
import saboroso.saborosoburguer.entities.menuItems.accompaniment.AddOn;
import saboroso.saborosoburguer.entities.menuItems.accompaniment.Combo;
import saboroso.saborosoburguer.entities.menuItems.burger.Burger;
import saboroso.saborosoburguer.entities.menuItems.burger.BurgerBread;
import saboroso.saborosoburguer.entities.soldItems.*;
import saboroso.saborosoburguer.entities.soldItems.accompaniment.AddOnSale;
import saboroso.saborosoburguer.entities.soldItems.accompaniment.ComboSale;
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

    // Mappers para DTO

    private final BurgerMapper burgerMapper;
    private final PortionMapper portionMapper;
    private final DrinkMapper drinkMapper;


    public OrderService(CostumerOrderRepository costumerOrderRepository, UserRepository userRepository, BurgerRepository burgerRepository, PortionRepository portionRepository, DrinkRepository drinkRepository, BurgerSaleRepository burgerSaleRepository, PortionSaleRepository portionSaleRepository, DrinkSaleRepository drinkSaleRepository, AddressRepository addressRepository, AddOnRepository addOnRepository, AddOnSaleRepository addOnSaleRepository, ComboRepository comboRepository, BreadRepository breadRepository, UserService userService, ComboSaleRepository comboSaleRepository, BurgerMapper burgerMapper, PortionMapper portionMapper, DrinkMapper drinkMapper) {
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
        this.burgerMapper = burgerMapper;
        this.portionMapper = portionMapper;
        this.drinkMapper = drinkMapper;
    }
    public void makeOrder(OrderForPostDTO orderForPostDTO) {
        UserEntity buyer = userRepository.findByPhoneNumber(orderForPostDTO.clientPhoneNumber());
        Address addressToDeliver = null;
        if (buyer == null) {
            UserAndAddress userAndAddress = userService.addClientUser(orderForPostDTO.clientName(), orderForPostDTO.clientPhoneNumber(), orderForPostDTO.addressToDeliver());
            buyer = userAndAddress.user();
            addressToDeliver = userAndAddress.address();
        }
        if (addressToDeliver == null) {
            addressToDeliver = addressRepository.findByContentAndBelongsTo(orderForPostDTO.addressToDeliver(), buyer);
        }
        if (addressToDeliver == null) {
            addressToDeliver = userService.addAddressToClient(buyer, orderForPostDTO.addressToDeliver());
        }

        CustomerOrder newOrder = new CustomerOrder(orderForPostDTO.orderCode(), orderForPostDTO.status(), buyer, addressToDeliver, orderForPostDTO.totalToPay(), orderForPostDTO.paymentMethod(), orderForPostDTO.howClientWillPay());

        List<BurgerSale> soldBurgers = new ArrayList<>();
        List<PortionSale> soldPortions = new ArrayList<>();
        List<DrinkSale> soldDrinks = new ArrayList<>();
        String notFoundOrDeleted = "não disponível ou excluído,";

        orderForPostDTO.burgers().forEach(purchasedBurger -> {
            Burger burger = burgerRepository.findBurgerByIdentifierAndDeletedFalseAndInStockTrue(purchasedBurger.identifier());
            if (burger == null) throw new NotFoundException("Hambúrguer " + notFoundOrDeleted);

            if (purchasedBurger.breadIdentifier() == null) throw new BadRequestException("Pão não informado, é necessário o pão!");
            BurgerBread bread = breadRepository.findByIdentifierAndDeletedFalseAndInStockTrue(purchasedBurger.breadIdentifier());
            if (bread == null) throw new NotFoundException("Pão " + notFoundOrDeleted);

            ComboSale comboSale = null;
            if (purchasedBurger.comboIdentifier() != null) {
                Combo combo = comboRepository.findByIdentifierAndDeletedFalseAndInStockTrue(purchasedBurger.comboIdentifier());
                if (combo == null) throw new NotFoundException("Combo"+ notFoundOrDeleted);
                comboSale = new ComboSale(combo);
            }

            List<AddOnSale> addOns = new ArrayList<>();
            feedAddOnSaleList(notFoundOrDeleted, addOns, purchasedBurger.addOnsIdentifiers());
            if (comboSale != null) comboSaleRepository.save(comboSale);
            soldBurgers.add(new BurgerSale(newOrder, burger, bread, comboSale, addOns, purchasedBurger.obs()));
        });

        orderForPostDTO.portions().forEach(purchasedPortion -> {
            Portion portion = portionRepository.findByIdentifierAndDeletedFalseAndInStockTrue(purchasedPortion.identifier());
            if (portion == null) throw new NotFoundException("Porção " + notFoundOrDeleted);

            List<AddOnSale> addOns = new ArrayList<>();
            feedAddOnSaleList(notFoundOrDeleted, addOns, purchasedPortion.addOnsIdentifiers());

            soldPortions.add(new PortionSale(newOrder, portion, addOns, purchasedPortion.obs()));
        });

        orderForPostDTO.drinks().forEach(purchasedDrink -> {
            Drink soldDrink = drinkRepository.findByIdentifierAndDeletedFalseAndInStockTrue(purchasedDrink.drinkIdentifier());
            if (soldDrink == null) throw new NotFoundException("Bebida " + notFoundOrDeleted);
            soldDrinks.add(new DrinkSale(newOrder, soldDrink, purchasedDrink.quantity()));
        });

        int items = 0;

        if (!soldBurgers.isEmpty()) {
            burgerSaleRepository.saveAll(soldBurgers);
            items++;
        }
        if (!soldPortions.isEmpty()) {
            portionSaleRepository.saveAll(soldPortions);
            items++;
        }
        if (!soldDrinks.isEmpty()) {
            drinkSaleRepository.saveAll(soldDrinks);
            items++;
        }
        if (items == 0) throw new BadRequestException("Pedido vazio.");

        customerOrderRepository.save(newOrder);
    }

    private void feedAddOnSaleList(String notFoundOrDeleted, List<AddOnSale> addOns, List<String> addOnsIdentifiers ) {
        addOnsIdentifiers.forEach(addOnIdentifier -> {
            AddOn addOn = addOnRepository.findByIdentifierAndDeletedFalseAndInStockTrue(addOnIdentifier);
            if (addOn == null) throw new NotFoundException("Adicional" + notFoundOrDeleted);
            addOns.add(new AddOnSale(addOn));
        });

        if (!addOns.isEmpty()) addOnSaleRepository.saveAll(addOns);
    }

    public List<OrderForGetDTO> getAllOrders() {
        List<CustomerOrder> allOrders = customerOrderRepository.findAll() ;
        return allOrders.stream().map(this::mapToDTO).toList();
    }

    public OrderForGetDTO mapToDTO(CustomerOrder order) {
        List<BurgerSale> soldBurgers = burgerSaleRepository.findAllByOrderThatSold(order);
        List<PortionSale> soldPortions = portionSaleRepository.findAllByOrderThatSold(order);
        List<DrinkSale> soldDrinks = drinkSaleRepository.findAllByOrderThatSold(order);

        List<BurgerForGetOrderDTO> burgers = new ArrayList<>();
        List<PortionForGetOrderDTO> portions = new ArrayList<>();
        List<DrinkAndQuantityForGetDTO> drinks = new ArrayList<>();

        if (soldBurgers != null) burgers = burgerMapper.severalToGetOrderDTO(soldBurgers);
        if (soldPortions != null) portions = portionMapper.severalToGetOrderDTO(soldPortions);
        if (soldDrinks != null) drinks = drinkMapper.severalToGetOrderDTO(soldDrinks);

        return new OrderForGetDTO(
                order.getOrderCode(),
                order.getOrderStatus(),
                order.getClientWhoOrdered().getName(),
                order.getClientWhoOrdered().getPhoneNumber(),
                order.getDeliveredAddress().getContent(),
                burgers,
                portions,
                drinks,
                order.getPaymentMethod(),
                order.getHowCustomerPaid(),
                order.getTotal()
        );
    }

}