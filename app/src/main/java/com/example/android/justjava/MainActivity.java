package com.example.android.justjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
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
        CheckBox whippedCreamCheckBox = findViewById(R.id.toppings_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        double totalPrice = calculatePrice();
        Log.i(TAG, "The price is " + totalPrice);
        String priceMessage = createOrderSummary(totalPrice, hasWhippedCream);
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
        else if (quantity == 100) {
            return;
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


    private String createOrderSummary(double totalPrice, boolean hasWhippedCream) {
        String priceMessage = "Name: Kaptain Kunal";
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, hasWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n" + getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(totalPrice));
        priceMessage += "\n" + getString(R.string.msg_thank_you);
        return priceMessage;
    }

}