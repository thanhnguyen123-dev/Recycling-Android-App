package com.example.recycleme;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleme.cart.Cart;
import com.example.recycleme.cart.CartViewAdapter;
import com.example.recycleme.cart.UserTree;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CartActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private CartViewAdapter adapter;
    private Cart itemsCart;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_cart, contentFrameLayout);

        this.itemsCart = Cart.getInstance();
        adapter = new CartViewAdapter(this.itemsCart.getItems());

        this.recyclerView = findViewById(R.id.itemsCartRecyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        this.saveButton = findViewById(R.id.save_button_cart);

        saveButton.setOnClickListener((click) -> {
            UserTree tree = UserTree.getInstance();
            tree.addItems(LocalDateTime.now(), new ArrayList<>(itemsCart.getItems()));
            itemsCart.clear();
            adapter.notifyDataSetChanged();
        });


    }
}