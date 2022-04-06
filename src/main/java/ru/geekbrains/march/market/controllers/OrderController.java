package ru.geekbrains.march.market.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.march.market.converters.OrderConverter;
import ru.geekbrains.march.market.dtos.OrderDto;
import ru.geekbrains.march.market.entities.User;
import ru.geekbrains.march.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.services.CartService;
import ru.geekbrains.march.market.services.OrderService;
import ru.geekbrains.march.market.services.UserService;

import java.security.Principal;

@RestController
@Data
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;
    private final CartService cartService;
    private final OrderConverter orderConverter;
    private final UserService userService;

    @GetMapping("/create")
    public OrderDto createNewOrder(Principal principal) {
        if (cartService.getCart().getItems().size() == 0) throw new ResourceNotFoundException("Cart is clear");
        String username = principal.getName();
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return orderConverter.entityToDto(orderService.createNewOrder(user, cartService.getCart()));
    }

}
