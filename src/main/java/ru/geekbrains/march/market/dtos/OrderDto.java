package ru.geekbrains.march.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class OrderDto {

    private Long id;
    private UserDto userDto;
    private List<OrderItemDto> items;
    private BigDecimal price;

}
