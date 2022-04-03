package ru.geekbrains.march.market.utils;

import lombok.Data;
import ru.geekbrains.march.market.entities.OrderItem;

import java.util.ArrayList;
import java.util.List;


@Data
public class Cart {

    private List<OrderItem> items;
    private int price;

    public Cart() {
        items = new ArrayList<>();
    }

    public void clear() {
        price = 0;
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
        price = 0;
        items.forEach(orderItem -> price += orderItem.getTotalPrice());
    }

}