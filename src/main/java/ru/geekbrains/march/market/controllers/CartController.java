package ru.geekbrains.march.market.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.converters.CartConverter;
import ru.geekbrains.march.market.dtos.CartDto;
import ru.geekbrains.march.market.services.CartService;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/change_quantity")
    public void changeProductQuantityInCart(@RequestParam Long id, @RequestParam Integer delta) {
        cartService.changeProductQuantity(id, delta);
    }

    @GetMapping("/set_quantity")
    public void setNewQuantity(@RequestParam Long id, @RequestParam Integer newQuantity) {
        cartService.setProductQuantity(id, newQuantity);
    }

    @GetMapping("/remove/{id}")
    public void removeProductFromCartById(@PathVariable Long id) {
        cartService.removeProductById(id);
    }

    @GetMapping("/add/{id}")
    public void addProduct(@PathVariable Long id) {
        cartService.addProductById(id);
    }

    @GetMapping
    public CartDto showCart() {
        return cartConverter.entityToDto(cartService.getCart());
    }

    @GetMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }

}