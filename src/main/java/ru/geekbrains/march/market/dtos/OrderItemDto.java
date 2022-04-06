package ru.geekbrains.march.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class OrderItemDto {

    private Long id;
    private ProductDto product;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;

}
