package com.example.pizzaworkshop.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.pizzaworkshop.model.Customer;
import com.example.pizzaworkshop.model.Order;
import com.example.pizzaworkshop.model.OrderManager;
import com.example.pizzaworkshop.model.Pizza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PizzaOrderController implements Initializable {

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField customerPhoneField;

    @FXML
    private ComboBox<String> pizzaTypeComboBox;

    @FXML
    private ComboBox<String> pizzaSizeComboBox;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private Button clearButton;

    @FXML
    private Button placeOrderButton;

    @FXML
    private TextArea orderSummaryArea;

    @FXML
    private Label orderCountLabel;

    // Model classes
    private OrderManager orderManager;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        orderManager = new OrderManager();

        ObservableList<String> pizzaTypes = FXCollections.observableArrayList(
                "Cheese", "Pepperoni", "Vegetarian", "Meat Lovers", "Hawaiian", "Supreme"
        );
        pizzaTypeComboBox.setItems(pizzaTypes);
        pizzaTypeComboBox.setValue("Cheese");

        ObservableList<String> pizzaSizes = FXCollections.observableArrayList(
                "Small", "Medium", "Large"
        );
        pizzaSizeComboBox.setItems(pizzaSizes);
        pizzaSizeComboBox.setValue("Medium");

        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1);
        quantitySpinner.setValueFactory(valueFactory);

        updateOrderCountLabel();
    }

    @FXML
    void handleClearButton(ActionEvent event) {
        customerNameField.clear();
        customerPhoneField.clear();
        pizzaTypeComboBox.setValue("Cheese");
        pizzaSizeComboBox.setValue("Medium");
        quantitySpinner.getValueFactory().setValue(1);
        orderSummaryArea.clear();
    }

    @FXML
    void handlePlaceOrderButton(ActionEvent event) {
        if (validateInput()) {
            Customer customer = new Customer(
                    customerNameField.getText(),
                    customerPhoneField.getText()
            );

            Pizza pizza = new Pizza(
                    pizzaTypeComboBox.getValue(),
                    pizzaSizeComboBox.getValue()
            );

            Order order = new Order(
                    customer,
                    pizza,
                    quantitySpinner.getValue()
            );

            orderManager.addOrder(order);

            updateOrderCountLabel();

            displayOrderSummary(order);
        }
    }

    private boolean validateInput() {
        if (customerNameField.getText().trim().isEmpty()) {
            orderSummaryArea.setText("Please enter customer name.");
            return false;
        }

        if (customerPhoneField.getText().trim().isEmpty()) {
            orderSummaryArea.setText("Please enter customer phone number.");
            return false;
        }

        return true;
    }

    private void displayOrderSummary(Order order) {
        String totalBeforeTax = String.format("%.2f", order.getTotalBeforeTax());
        String totalToPay = String.format("%.2f", order.getTotalToPay());

        StringBuilder summary = new StringBuilder();
        summary.append("Customer Name: ").append(order.getCustomer().getName()).append("\n");
        summary.append("Customer Phone: ").append(order.getCustomer().getPhone()).append("\n");
        summary.append("Pizza Type: ").append(order.getPizza().getType()).append("\n");
        summary.append("Pizza Size: ").append(order.getPizza().getSize()).append("\n");
        summary.append("Quantity: ").append(order.getQuantity()).append("\n");
        summary.append("Total before tax: $").append(totalBeforeTax).append("\n");
        summary.append("Total to be paid: $").append(totalToPay).append("\n");

        orderSummaryArea.setText(summary.toString());
    }

    private void updateOrderCountLabel() {
        orderCountLabel.setText("Total Orders: " + orderManager.getOrderCount());
    }
}