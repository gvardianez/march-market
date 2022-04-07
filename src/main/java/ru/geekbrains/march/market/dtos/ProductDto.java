package ru.geekbrains.march.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductDto {

    private Long id;
    private String title;
    private BigDecimal price;
    private String categoryTitle;

}
