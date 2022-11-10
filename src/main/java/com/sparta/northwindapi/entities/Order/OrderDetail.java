package com.sparta.northwindapi.entities.Order;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "`order details`")
public class OrderDetail {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private OrderDetailId id;

    @NotNull
    @Column(name = "UnitPrice", nullable = false, precision = 10, scale = 4)
    private BigDecimal unitPrice;

    @NotNull
    @Column(name = "Quantity", nullable = false)
    private Short quantity;

    @NotNull
    @Column(name = "Discount", nullable = false)
    private Double discount;

    public OrderDetailId getId() {
        return id;
    }

    public void setId(OrderDetailId id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Short getQuantity() {
        return quantity;
    }

    public void setQuantity(Short quantity) {
        this.quantity = quantity;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

}