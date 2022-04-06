package ru.geekbrains.march.market.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.march.market.utils.Cart;
import ru.geekbrains.march.market.entities.OrderItem;
import ru.geekbrains.march.market.exceptions.FieldValidationException;
import ru.geekbrains.march.market.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;

@Service
@Data
@RequiredArgsConstructor
public class CartService {

    private Cart cart;
    private final ProductService productService;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public void changeProductQuantity(Long id, Integer delta) {
        cart.getItems()
                .stream()
                .filter(orderItem -> orderItem.getProduct().getId().equals(id))
                .findFirst()
                .ifPresentOrElse(orderItem -> {
                            if ((orderItem.getQuantity() + delta) <= 0) {
                                cart.remove(orderItem);
                                return;
                            }
                            orderItem.changeQuantity(delta);
                            cart.recalculate();
                        },
                        () -> {
                            if (delta > 0)
                                cart.add(new OrderItem(productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id = " + id)), delta));
                        }
                );
// вот такой вариант получился циклами, что-то не особо он мне нравится, проверку if (delta > 0) делаем на случай, если товара не нашли в корзине, и delta <=0 оказалась(т.е добавлять не надо)
//        for (OrderItem orderItem : cart.getItems()) {
//            if (orderItem.getProduct().getId().equals(id)) {
//                if ((orderItem.getQuantity() + delta) <= 0) {
//                    cart.remove(orderItem);
//                    return;
//                }
//                orderItem.changeQuantity(delta);
//                cart.recalculate();
//                return;
//            }
//        }
//        if (delta > 0) {
//            cart.add(new OrderItem(productService.getProduct(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id = " + id)), delta));
//            cart.recalculate();
//        }
    }

    public void setProductQuantity(Long id, Integer newQuantity) {
        if (newQuantity < 0) throw new FieldValidationException("New quantity must be at least zero");
        cart.getItems()
                .stream()
                .filter(orderItem -> orderItem.getProduct().getId().equals(id))
                .findFirst()
                .ifPresentOrElse(orderItem -> {
                            if (newQuantity == 0) {
                                cart.remove(orderItem);
                                return;
                            }
                            orderItem.setQuantity(newQuantity);
                        },
                        () -> {
                            throw new ResourceNotFoundException("Product not found, id = " + id);
                        }
                );
        cart.recalculate();
    }

    public void removeProductById(Long id) {
        cart.getItems().removeIf(orderItem -> orderItem.getProduct().getId().equals(id));
        cart.recalculate();
    }

    public void clearCart() {
        cart.clear();
    }


}
