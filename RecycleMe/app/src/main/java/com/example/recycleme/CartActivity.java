package com.example.recycleme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.cardview.widget.CardView;



import java.util.ArrayList;

public class CartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_cart, contentFrameLayout);

        Cart cart = retrieveCart();
        displayCartItems(cart);
    }

    private void displayCartItems(Cart cart) {
        CardView cardView = findViewById(R.id.cart_view);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        LayoutInflater inflater = getLayoutInflater();

        for (RecycledItem item : cart.getItems()) {
            View view = inflater.inflate(R.layout.cart_items, null, false);  // Changed here

            TextView productName = view.findViewById(R.id.product_name_text);
            TextView productBrand = view.findViewById(R.id.product_brand_text);
            TextView productMaterial = view.findViewById(R.id.product_material_text);
            TextView productPrice = view.findViewById(R.id.product_value_text);

            productName.setText(item.getItem());
            productBrand.setText(item.getBrandName());
            productMaterial.setText(item.getMaterial());
            productPrice.setText(String.valueOf(item.getValue()));

            linearLayout.addView(view);
        }

        cardView.addView(linearLayout);
    }


    private Cart retrieveCart() {
        // For demonstration purposes, I'll just create some dummy cart items here
        ArrayList<RecycledItem> dummyItems = new ArrayList<>();
        dummyItems.add(new RecycledItem("Product 1", "Brand 1", "Material 1", 10.0));
        dummyItems.add(new RecycledItem("Product 2", "Brand 2", "Material 2", 20.0));
        dummyItems.add(new RecycledItem("Product 3", "Brand 3", "Material 3", 30.0));
        dummyItems.add(new RecycledItem("Product 4", "Brand 4", "Material 4", 40.0));
        dummyItems.add(new RecycledItem("Product 5", "Brand 5", "Material 5", 50.0));
        dummyItems.add(new RecycledItem("Product 6", "Brand 6", "Material 6", 60.0));
        dummyItems.add(new RecycledItem("Product 7", "Brand 7", "Material 7", 70.0));
        dummyItems.add(new RecycledItem("Product 8", "Brand 8", "Material 8", 80.0));
        dummyItems.add(new RecycledItem("Product 9", "Brand 9", "Material 9", 90.0));
        dummyItems.add(new RecycledItem("Product 10", "Brand 10", "Material 10", 100.0));
        dummyItems.add(new RecycledItem("Product 11", "Brand 11", "Material 11", 110.0));
        dummyItems.add(new RecycledItem("Product 12", "Brand 12", "Material 12", 120.0));
        return new Cart(dummyItems);
    }
}
