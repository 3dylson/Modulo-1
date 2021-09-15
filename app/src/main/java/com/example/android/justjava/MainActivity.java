package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;
import java.util.Objects;

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
        TextInputEditText nameField = findViewById(R.id.name_text_input);
        if (TextUtils.isEmpty(nameField.getText().toString())) {
            nameField.setError(getString(R.string.error_name_empty));
            return;
        } else {
            nameField.setError(null);
        }

        if (quantity == 0) {
            displayMessage(getString(R.string.error_order_invalid));
            return;
        }

        String name = Objects.requireNonNull(nameField.getText()).toString();

        CheckBox whippedCreamCheckBox = findViewById(R.id.toppings_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        double totalPrice = calculatePrice(hasChocolate, hasWhippedCream);
        Log.i(TAG, "The price is " + totalPrice);
        String order = createOrderSummary(totalPrice, hasWhippedCream, hasChocolate, name);
        //displayMessage(order);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.order_summary_email_subject, name));
        intent.putExtra(Intent.EXTRA_TEXT, order);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
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
    private double calculatePrice(boolean hasChocolate, boolean hasWhippedCream) {
        double total = coffeePrice;

        if (hasWhippedCream) {
            total+= 0.20;
        }

        if (hasChocolate) {
            total+= 0.30;
        }

        return quantity * total;
    }


    private String createOrderSummary(double totalPrice, boolean hasChocolate, boolean hasWhippedCream, String name) {
        String priceMessage = getString(R.string.order_summary_name, name);
        priceMessage += "\n" + getString(R.string.order_summary_whipped_cream, hasWhippedCream);
        priceMessage += "\n" + getString(R.string.order_summary_chocolate, hasChocolate);
        priceMessage += "\n" + getString(R.string.order_summary_quantity, quantity);
        priceMessage += "\n" + getString(R.string.order_summary_price, NumberFormat.getCurrencyInstance().format(totalPrice));
        priceMessage += "\n" + getString(R.string.msg_thank_you);
        return priceMessage;
    }

}