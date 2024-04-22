package com.example.recycleme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
        return Cart.getInstance();
    }
}
