package ru.mooncess.Pizzeria.services;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.mooncess.Pizzeria.dto.order.OrderDTO;
import ru.mooncess.Pizzeria.entities.Order;
import ru.mooncess.Pizzeria.entities.OrderItem;
import ru.mooncess.Pizzeria.entities.User;
import ru.mooncess.Pizzeria.entities.enums.OrderStatus;
import ru.mooncess.Pizzeria.exceptions.BasketIsEmptyException;
import ru.mooncess.Pizzeria.mappers.OrderItemMapper;
import ru.mooncess.Pizzeria.mappers.OrderMapper;
import ru.mooncess.Pizzeria.repositories.OrderItemRepository;
import ru.mooncess.Pizzeria.repositories.OrderRepository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final OrderItemMapper mapperItem;
    private final UserService userService;
    private final OrderItemRepository orderItemRepository;

    public OrderDTO create(User user, String address) {
        List<OrderItem> basketList = user.getBasket().getOrderItemList();
        if (basketList.size() == 0) {
            throw new BasketIsEmptyException();
        }
        Order order = new Order();
        order.setAddress(address);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        order.setCreationDate(LocalDateTime.now().format(formatter));
        order.setStatus(OrderStatus.ACCEPTED);
        order.setBuyer(user);
        repository.save(order);
        float totalPrice = 0;
        String decription = "";
        for (OrderItem i : basketList) {
            totalPrice += i.getPrice() * i.getQuantity();
            decription += i.getQuantity() + " x " + i.getDescription() + " (" + i.getPrice() + ") ; ";
            orderItemRepository.deleteById(i.getId());
        }
        order.setDescription(decription);
        order.setTotal(totalPrice);
        repository.save(order);
        return mapper.toDto(order);
    }

    public List<OrderDTO> findAllByBuyerId(User user) {
        return repository.findAllByBuyerId(user.getId()).stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public Order findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<OrderDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

//    public List<OrderListDTO> findAllByUserId(Long id) {
//        return repository.findAllByBuyerId(id).stream().map(mapper::toListDto).collect(Collectors.toList());
//    }
//
//    public OrderCreateDTO findById(Long id) {
//        return mapper.toCreateDto(repository.findById(id).orElse(null));
//    }
//
//    public List<OrderItemListDTO> findAllByOrderId(Long id) {
//        return Objects.requireNonNull(repository.findById(id).orElse(null)).getOrderItems().stream().map(mapperItem::toListDto).collect(Collectors.toList());
//    }
//
    public Order update(Order order) {
        return repository.save(order);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}