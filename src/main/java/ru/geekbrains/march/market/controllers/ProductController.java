package ru.geekbrains.march.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.converters.ProductConverter;
import ru.geekbrains.march.market.dtos.ProductDto;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.services.CategoryService;
import ru.geekbrains.march.market.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;
    private final CategoryService categoryService;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.findAll().stream().map(productConverter::entityToDto).collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createNewProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        Product product = productConverter.dtoToEntity(productDto, categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Категория с названием: " + productDto.getCategoryTitle() + " не найдена")));
        return productConverter.entityToDto(productService.createNewProduct(product));
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

}
