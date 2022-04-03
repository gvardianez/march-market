package ru.geekbrains.march.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import ru.geekbrains.march.market.utils.Cart;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "order")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order(User user, Cart cart) {
        this.price = cart.getPrice();
        this.user = user;
        this.orderItems = new ArrayList<>();
        cart.getItems().forEach(orderItem -> {
            orderItem.setOrder(this);
            orderItems.add(orderItem);
        });
        cart.clear();
    }

}
