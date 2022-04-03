package ru.geekbrains.march.market.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.utils.Cart;
import ru.geekbrains.march.market.entities.Order;
import ru.geekbrains.march.market.entities.User;
import ru.geekbrains.march.market.repositories.OrderRepository;

import java.security.Principal;

@Service
@Data
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    public Order createNewOrder(Principal principal, Cart cart) {
        String username = principal.getName();
        User user = userService.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(String.format("User '%s' not found", username)));
        return orderRepository.save(new Order(user, cart));
    }

    public Order getOrder(Long id) {
        return orderRepository.getById(id);
    }

}
