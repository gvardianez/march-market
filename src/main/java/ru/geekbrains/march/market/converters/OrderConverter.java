package ru.geekbrains.march.market.converters;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.dtos.OrderDto;
import ru.geekbrains.march.market.dtos.OrderItemDto;
import ru.geekbrains.march.market.entities.Order;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OrderConverter {

    private final OrderItemConverter orderItemConverter;
    private final UserConverter userConverter;

    public OrderDto entityToDto(Order order) {
        List<OrderItemDto> orderItemDtoList = order.getOrderItems()
                .stream()
                .map(orderItemConverter::entityToDto)
                .collect(Collectors.toList());
        return new OrderDto(order.getId(), userConverter.entityToDto(order.getUser()), orderItemDtoList, order.getPrice());
    }

}
