package ru.geekbrains.march.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.utils.Cart;
import ru.geekbrains.march.market.dtos.CartDto;
import ru.geekbrains.march.market.dtos.OrderItemDto;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {

    private final OrderItemConverter orderItemConverter;

    public CartDto entityToDto(Cart cart) {
        List<OrderItemDto> orderItemDtoList = cart.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList());
        return new CartDto(orderItemDtoList, cart.getPrice());
    }

}
