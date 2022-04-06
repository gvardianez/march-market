package ru.geekbrains.march.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "order_items")
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
        calculateTotalPrice(quantity);
    }

    public void changeQuantity(int delta) {
        this.quantity += delta;
        calculateTotalPrice(quantity);
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
        calculateTotalPrice(quantity);
    }

    private void calculateTotalPrice(int quantity) {
        this.totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}