package com.adarshgupta.pizzadelivery;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int quantityCS = 0,quantityF = 0,quantityBC = 0,quantityCF = 0, quantity = 0;
    float total = 0;
    float tax = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    //For Country Special pizza
    public void incrementCS(View view){
        if(quantityCS == 25){
            //no allowing more than 25 pizzas to order
            Toast toast = Toast.makeText(this, "Cannot be ordered more than 25", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantityCS = quantityCS + 1;
        Toast toast = Toast.makeText(this, "Country Special Added!", Toast.LENGTH_SHORT);
        toast.show();
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    //For Country Special pizza
    public void decrementCS(View view){
        if(quantityCS == 0){
            //atleast 1 pizza
            Toast toast = Toast.makeText(this, "Order Atleast 1", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantityCS = quantityCS - 1;
        Toast toast = Toast.makeText(this, "Country Special Removed!", Toast.LENGTH_SHORT);
        toast.show();
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    //For Farmhouse pizza
    public void incrementF(View view){
        if(quantityF == 25){
            //no allowing more than 25 pizzas to order
            Toast toast = Toast.makeText(this, "Cannot be ordered more than 25", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantityF = quantityF + 1;
        Toast toast = Toast.makeText(this, "Farmhouse Added!", Toast.LENGTH_SHORT);
        toast.show();
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    //For Farmhouse pizza
    public void decrementF(View view){
        if(quantityF == 0){
            //atleast 1 pizza
            Toast toast = Toast.makeText(this, "Order Atleast 1", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantityF = quantityF - 1;
        Toast toast = Toast.makeText(this, "Farmhouse Removed!", Toast.LENGTH_SHORT);
        toast.show();
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    //For Barbeque Chicken pizza
    public void incrementBC(View view){
        if(quantityBC == 25){
            //no allowing more than 25 pizzas to order
            Toast toast = Toast.makeText(this, "Cannot be ordered more than 25", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantityBC = quantityBC + 1;
        Toast toast = Toast.makeText(this, "Barbeque Chicken Added!", Toast.LENGTH_SHORT);
        toast.show();
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    //For Barbeque Chicken pizza
    public void decrementBC(View view){
        if(quantityBC == 0){
            //atleast 1 pizza
            Toast toast = Toast.makeText(this, "Order Atleast 1", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantityBC = quantityBC - 1;
        Toast toast = Toast.makeText(this, "Barbeque Chicken Removed!", Toast.LENGTH_SHORT);
        toast.show();
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    //For Chicken Fiesta pizza
    public void incrementCF(View view){
        if(quantityCF == 25){
            //no allowing more than 25 pizzas to order
            Toast toast = Toast.makeText(this, "Cannot be ordered more than 25", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantityCF = quantityCF + 1;
        Toast toast = Toast.makeText(this, "Chicken Fiesta Added!", Toast.LENGTH_SHORT);
        toast.show();
        quantity = quantity + 1;
        displayQuantity(quantity);
    }
    //For Chicken Fiesta pizza
    public void decrementCF(View view){
        if(quantityCF == 0){
            //atleast 1 pizza
            Toast toast = Toast.makeText(this, "Order Atleast 1", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        quantityCF = quantityCF - 1;
        Toast toast = Toast.makeText(this, "Chicken Fiesta Removed!", Toast.LENGTH_SHORT);
        toast.show();
        quantity = quantity - 1;
        displayQuantity(quantity);
    }







    public void submitOrder(View view){

        //getting customer's name
        EditText nameField = (EditText)findViewById(R.id.name_field);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();

        CheckBox toppingCheckbox = (CheckBox)findViewById(R.id.topping_checkbox);
        boolean extraTopping = toppingCheckbox.isChecked();

        CheckBox cheeseCheckbox = (CheckBox)findViewById(R.id.cheese_checkbox);
        boolean extraCheese = cheeseCheckbox.isChecked();

        float price = calculatePrice(extraTopping, extraCheese);

        TextView totalTextView = (TextView) findViewById(R.id.total_text_view);
        totalTextView.setText("Total : Rs. " + price);

        TextView taxTextView = (TextView) findViewById(R.id.tax_text_view);
        taxTextView.setText("Tax : Rs. " + tax);


        String message = createOrderSummary(name, price);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your order at Pizza Delivery!"+name);
        intent.putExtra(Intent.EXTRA_TEXT, message);

        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

    }


        private float calculatePrice(boolean addextraTopping, boolean addextraCheese){
        // First calculate the price of one cup of coffee
        int PriceCS = 295;
        int PriceF = 300;
        int PriceBC = 315;
        int PriceCF = 330;
        int extraTopping = 0;
        int extraCheese = 0;


        if (addextraTopping) {
            extraTopping = 1;
        }


        if (addextraCheese) {
            extraCheese = 1;
        }

            total = ((quantityCS * PriceCS)+(quantityF * PriceF)+(quantityBC * PriceBC)+(quantityCF * PriceCF)+(extraTopping*50)+(extraCheese*100));
            tax = (float)(total*0.143);

        return total;
    }

    private String createOrderSummary(String name, float price) {
        String receipt = "Thank You for choosing 'Pizza Delivery! Total : "+total+" Tax : "+tax+" Thank you for ordering!Your pizza will reach within 30minutes.";
        return receipt;
    }

    private void displayQuantity(int pizzas) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("Pizzas : " + pizzas);
    }


}
