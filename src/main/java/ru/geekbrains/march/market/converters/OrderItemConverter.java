package ru.geekbrains.march.market.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.dtos.OrderItemDto;
import ru.geekbrains.march.market.entities.OrderItem;

@Component
@AllArgsConstructor
public class OrderItemConverter {

    private final ProductConverter productConverter;

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getId(), productConverter.entityToDto(orderItem.getProduct()), orderItem.getQuantity(), orderItem.getUnitPrice(), orderItem.getTotalPrice());
    }

}
