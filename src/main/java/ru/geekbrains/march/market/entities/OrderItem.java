package ru.geekbrains.march.market.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private int unitPrice;

    @Column(name = "total_price")
    private int totalPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = product.getPrice();
        this.totalPrice = unitPrice * quantity;
    }

    public void changeQuantity(int delta) {
        this.quantity += delta;
        this.totalPrice = this.unitPrice * this.quantity;
    }

    public void setQuantity(int newQuantity) {
        this.quantity = newQuantity;
        this.totalPrice = this.unitPrice * this.quantity;
    }

}