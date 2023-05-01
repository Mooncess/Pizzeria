package ru.mooncess.Pizzeria.dto.orderitem;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Data
public class OrderItemForCotroller {
    Short quantity;
    String size;
    String dough;
    List<Long> additivesId;
}
