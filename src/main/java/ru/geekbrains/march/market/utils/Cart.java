package ru.geekbrains.march.market.utils;

import lombok.Data;
import ru.geekbrains.march.market.entities.OrderItem;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Data
public class Cart {

    private List<OrderItem> items;
    private BigDecimal price;

    public Cart() {
        items = new ArrayList<>();
    }

    public void clear() {
        price = BigDecimal.ZERO;
        items.clear();
    }

    public void add(OrderItem orderItem) {
        items.add(orderItem);
        recalculate();
    }

    public void remove(OrderItem orderItem) {
        items.remove(orderItem);
        recalculate();
    }

    public void recalculate() {
        price = BigDecimal.ZERO;
        items.forEach(orderItem -> price = price.add(orderItem.getTotalPrice()));
    }

}