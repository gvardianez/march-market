package ru.geekbrains.march.market.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.entities.OrderItem;
import ru.geekbrains.march.market.entities.Product;
import ru.geekbrains.march.market.repositories.OrderItemRepository;
import ru.geekbrains.march.market.utils.Cart;
import ru.geekbrains.march.market.entities.Order;
import ru.geekbrains.march.market.entities.User;
import ru.geekbrains.march.market.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderItemRepository orderItemRepository;

//        @Transactional
    public Order createNewOrder(User user, Cart cart) {
//        Order order = new Order(user, cart);
//        Product product = productService.getProduct(1L).orElseThrow();
//        List<OrderItem> orderItems = new ArrayList<>();
//        long currentTime = System.currentTimeMillis();
//        for (int i = 0; i < 300000; i++) {
//            int q = (int) (Math.random() + 1);
//            orderItemRepository.save(new OrderItem(product,q));
//        }
//        long delta = System.currentTimeMillis() - currentTime;
//        order.setOrderItems(orderItems);
//        order = orderRepository.save(order);
//        return orderRepository.save(new Order(user, cart));
//        System.out.println(delta);
        return orderRepository.save(new Order(user, cart));
    }

    public Order getOrder(Long id) {
        return orderRepository.getById(id);
    }

}
