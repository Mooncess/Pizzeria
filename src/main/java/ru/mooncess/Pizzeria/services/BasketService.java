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
import ru.mooncess.Pizzeria.repositories.OrderItemRepository;
import ru.mooncess.Pizzeria.repositories.ProductRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BasketService {
    private final ProductRepository productRepository;
    private final AdditiveMapper additiveMapper;
    private final OrderItemMapper orderItemMapper;
    private final OrderItemRepository orderItemRepository;
    private final AdditiveRepository additiveRepository;
    public Boolean addToBasket(User user, Long id, OrderItemForCotroller orderItemForCotroller) {
        Product product = productRepository.findById(id).orElseThrow();
        String description = product.getDescription();
        String size = orderItemForCotroller.getSize();
        String dough = orderItemForCotroller.getDough();
        Short quantity = orderItemForCotroller.getQuantity();
        if (size != null) {
            description += " " + size + ", " + dough + ".";
            if (size == "STANDARD") {
                product.setPrice(product.getPrice()+150);
            }
            else if (size == "LARGE") {
                product.setPrice(product.getPrice()+300);
            }
            for (Long i : orderItemForCotroller.getAdditivesId()) {
                Additive temp = additiveRepository.findById(i).orElseThrow();
                description += " " + temp.getTitle();
                product.setPrice(product.getPrice()+temp.getPrice());
            }
            product.setDescription(description);
        }
        CreateOrderItemDTO createOrderItemDTO = new CreateOrderItemDTO();
        createOrderItemDTO.setTitle(product.getTitle());
        createOrderItemDTO.setQuantity(quantity);
        createOrderItemDTO.setPrice(product.getPrice());
        createOrderItemDTO.setDescription(description);
        createOrderItemDTO.setBasket(user.getBasket());
        Basket userBasket = user.getBasket();
        createOrderItemDTO.setBasket(userBasket);
        orderItemRepository.save(orderItemMapper.toEntity(createOrderItemDTO));
        return true;
    }

    public Basket getBasketByUser(User user) {
        return user.getBasket();
    }

    // Доделать
    public Basket deleteFromBasket(User user, Long id) {
        Basket userBasket = user.getBasket();
        return getBasketByUser(user);
    }
}
