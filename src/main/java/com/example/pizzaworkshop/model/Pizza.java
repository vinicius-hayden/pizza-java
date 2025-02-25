package com.example.pizzaworkshop.model;

public class Pizza {
    private String type;
    private String size;
    private double basePrice;

    private static final double SMALL_PRICE = 8.99;
    private static final double MEDIUM_PRICE = 10.99;
    private static final double LARGE_PRICE = 12.99;

    private static final double SPECIALTY_EXTRA = 2.00;

    public Pizza(String type, String size) {
        this.type = type;
        this.size = size;
        this.basePrice = calculateBasePrice();
    }

    private double calculateBasePrice() {
        double price = 0.0;

        switch (size) {
            case "Small":
                price = SMALL_PRICE;
                break;
            case "Medium":
                price = MEDIUM_PRICE;
                break;
            case "Large":
                price = LARGE_PRICE;
                break;
        }

        if (!type.equals("Cheese") && !type.equals("Pepperoni")) {
            price += SPECIALTY_EXTRA;
        }

        return price;
    }

    public String getType() {
        return type;
    }

    public String getSize() {
        return size;
    }

    public double getBasePrice() {
        return basePrice;
    }

    @Override
    public String toString() {
        return "Pizza: " + type + " (" + size + ") - $" + basePrice;
    }
}
