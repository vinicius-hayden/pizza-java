module com.example.pizzaworkshop {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.pizzaworkshop.controller to javafx.fxml;

    exports com.example.pizzaworkshop;
}
