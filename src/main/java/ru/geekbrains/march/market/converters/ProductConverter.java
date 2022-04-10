package ru.geekbrains.march.market.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.march.market.dtos.ProductDto;
import ru.geekbrains.march.market.entities.Category;
import ru.geekbrains.march.market.entities.Product;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    public Product dtoToEntity(ProductDto productDto, Category category) {
        return new Product(productDto.getId(), productDto.getTitle(), productDto.getPrice(), category);
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(), product.getCategory().getTitle());
    }

}
