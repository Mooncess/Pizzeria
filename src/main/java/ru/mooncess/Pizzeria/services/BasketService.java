package ru.mooncess.Pizzeria.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mooncess.Pizzeria.dto.additive.AdditiveAddToPizzaDTO;
import ru.mooncess.Pizzeria.dto.orderitem.CreateOrderItemDTO;
import ru.mooncess.Pizzeria.dto.orderitem.OrderItemForCotroller;
import ru.mooncess.Pizzeria.entities.*;
import ru.mooncess.Pizzeria.mappers.AdditiveMapper;
import ru.mooncess.Pizzeria.mappers.OrderItemMapper;
import ru.mooncess.Pizzeria.repositories.AdditiveRepository;
import ru.mooncess.Pizzeria.repositories.BasketRepository;
import ru.mooncess.Pizzeria.repositories.OrderItemRepository;
import ru.mooncess.Pizzeria.repositories.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BasketService {
    private final ProductRepository productRepository;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;
    private final AdditiveRepository additiveRepository;
    public Boolean addToBasket(User user, Long id, OrderItemForCotroller orderItemForCotroller) {
        Product product = productRepository.findById(id).orElseThrow();
        String description = product.getTitle();
        float price = product.getPrice();
        String size = orderItemForCotroller.getSize();
        String dough = orderItemForCotroller.getDough();
        Short quantity = orderItemForCotroller.getQuantity();
        if (size != null) {
            if (size.equals("STANDARD")) {
                price = product.getPrice()+150;
                description += ". Размер: Средняя";
            }
            else if (size.equals("LARGE")) {
                price = product.getPrice()+300;
                description += ". Размер: Большая";
            }
            else {
                description += ". Размер: Маленькая";
            }
            if (dough.equals("TRADITIONAL")) {
                description += ". Тесто: Традиционное;";
            }
            else {
                description += ". Тесто: Тонкое;";
            }
            if (!orderItemForCotroller.getAdditivesId().isEmpty()){
                description += " +";
                for (Long i : orderItemForCotroller.getAdditivesId()) {
                    Additive temp = additiveRepository.findById(i).orElseThrow();
                    description += " " + temp.getTitle();
                    price += temp.getPrice();
                }
            }
        }
        if (orderItemRepository.findByDescriptionAndBasketId(description, user.getBasket().getId()) == null ){
            CreateOrderItemDTO createOrderItemDTO = new CreateOrderItemDTO();
            createOrderItemDTO.setTitle(product.getTitle());
            createOrderItemDTO.setQuantity(quantity);
            createOrderItemDTO.setPrice(price);
            createOrderItemDTO.setDescription(description);
            createOrderItemDTO.setBasket(user.getBasket());
            Basket userBasket = user.getBasket();
            createOrderItemDTO.setBasket(userBasket);
            orderItemRepository.save(orderItemMapper.toEntity(createOrderItemDTO));
        }
        else {
            OrderItem orderItem = orderItemRepository.findByDescriptionAndBasketId(description, user.getBasket().getId());
            orderItem.setQuantity((short) (orderItem.getQuantity()+quantity));
            orderItemRepository.save(orderItem);
        }
        return true;
    }

    public List<OrderItem> getItemsInBasketByUser(User user) {
        return orderItemRepository.findAllByBasketId(user.getBasket().getId());
    }

    // Доделать
//    public Basket deleteFromBasket(User user, Long id) {
//        Basket userBasket = user.getBasket();
//        return getBasketByUser(user);
//    }
}
