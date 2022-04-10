package ru.geekbrains.march.market.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.utils.Cart;
import ru.geekbrains.march.market.entities.Order;
import ru.geekbrains.march.market.entities.User;
import ru.geekbrains.march.market.repositories.OrderRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional
    public Order createNewOrder(User user, Cart cart) {
        return orderRepository.save(new Order(user, cart));
    }

    public Order getOrder(Long id) {
        return orderRepository.getById(id);
    }

}
