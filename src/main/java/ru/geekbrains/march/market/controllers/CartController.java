package ru.geekbrains.march.market.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.march.market.converters.CartConverter;
import ru.geekbrains.march.market.dtos.CartDto;
import ru.geekbrains.march.market.services.CartService;
import ru.geekbrains.march.market.services.ProductService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/change_quantity")
    @ResponseStatus(HttpStatus.OK)
    public void changeProductQuantityInCart(@RequestParam Long id, @RequestParam Integer delta) {
        cartService.changeProductQuantity(id, delta);
    }

    @GetMapping("/set_quantity")
    @ResponseStatus(HttpStatus.OK)
    public void setNewQuantity(@RequestParam Long id, @RequestParam Integer newQuantity) {
        cartService.setProductQuantity(id, newQuantity);
    }

    @GetMapping("/remove/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeProductFromCartById(@PathVariable Long id) {
        cartService.removeProductById(id);
    }

    @GetMapping
    public CartDto showCart() {
        return cartConverter.entityToDto(cartService.getCart());
    }

    @GetMapping("/clear")
    @ResponseStatus(HttpStatus.OK)
    public void clearCart() {
        cartService.clearCart();
    }

}