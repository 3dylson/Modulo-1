package com.example.android.justjava;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    double coffeePrice = 0.5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        double totalPrice = calculatePrice();
        String priceMessage = createOrderSummary(totalPrice);
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 0) {
            Button decrementButton = findViewById(R.id.decrement_button);
            decrementButton.setEnabled(true);
        }
        quantity++;
        displayQuantity(quantity);
    }


    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity--;
        if (quantity == 0) {
            Button decrementButton = findViewById(R.id.decrement_button);
            decrementButton.setEnabled(false);
        }
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private double calculatePrice() {
        return quantity * coffeePrice;
    }


    private String createOrderSummary(double totalPrice) {
        return "Name: " + "Kaptain Kunal\n" + "Quantity: " + quantity + "\n" + "Total: $" + totalPrice + "\n" + "Thank You!";
    }

}