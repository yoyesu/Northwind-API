package com.sparta.northwindapi.entities.Order;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders")
public class Order extends RepresentationModel<Order> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID", nullable = false)
    private Integer id;

    @Column(name = "CustomerID")
    private String customerID;

    @Column(name = "EmployeeID")
    private int employeeID;

    @Column(name = "OrderDate")
    private Instant orderDate;

    @Column(name = "RequiredDate")
    private Instant requiredDate;

    @Column(name = "ShippedDate")
    private Instant shippedDate;

    @Column(name = "Freight", precision = 10, scale = 4)
    private BigDecimal freight;

    @Size(max = 40)
    @Column(name = "ShipName", length = 40)
    private String shipName;

    @Size(max = 60)
    @Column(name = "ShipAddress", length = 60)
    private String shipAddress;

    @Size(max = 15)
    @Column(name = "ShipCity", length = 15)
    private String shipCity;

    @Size(max = 15)
    @Column(name = "ShipRegion", length = 15)
    private String shipRegion;

    @Size(max = 10)
    @Column(name = "ShipPostalCode", length = 10)
    private String shipPostalCode;

    @Size(max = 15)
    @Column(name = "ShipCountry", length = 15)
    private String shipCountry;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public Instant getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Instant orderDate) {
        this.orderDate = orderDate;
    }

    public Instant getRequiredDate() {
        return requiredDate;
    }

    public void setRequiredDate(Instant requiredDate) {
        this.requiredDate = requiredDate;
    }

    public Instant getShippedDate() {
        return shippedDate;
    }

    public void setShippedDate(Instant shippedDate) {
        this.shippedDate = shippedDate;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getShipAddress() {
        return shipAddress;
    }

    public void setShipAddress(String shipAddress) {
        this.shipAddress = shipAddress;
    }

    public String getShipCity() {
        return shipCity;
    }

    public void setShipCity(String shipCity) {
        this.shipCity = shipCity;
    }

    public String getShipRegion() {
        return shipRegion;
    }

    public void setShipRegion(String shipRegion) {
        this.shipRegion = shipRegion;
    }

    public String getShipPostalCode() {
        return shipPostalCode;
    }

    public void setShipPostalCode(String shipPostalCode) {
        this.shipPostalCode = shipPostalCode;
    }

    public String getShipCountry() {
        return shipCountry;
    }

    public void setShipCountry(String shipCountry) {
        this.shipCountry = shipCountry;
    }

}