package com.example.pizzaworkshop.model;

public class Order {
    private Customer customer;
    private Pizza pizza;
    private int quantity;
    private static final double TAX_RATE = 0.13; // 13% tax

    public Order(Customer customer, Pizza pizza, int quantity) {
        this.customer = customer;
        this.pizza = pizza;
        this.quantity = quantity;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalBeforeTax() {
        return pizza.getBasePrice() * quantity;
    }

    public double getTax() {
        return getTotalBeforeTax() * TAX_RATE;
    }

    public double getTotalToPay() {
        return getTotalBeforeTax() + getTax();
    }

    @Override
    public String toString() {
        return "Order: " + customer.getName() + ", " + pizza.getType() + " pizza, Quantity: " + quantity;
    }
}