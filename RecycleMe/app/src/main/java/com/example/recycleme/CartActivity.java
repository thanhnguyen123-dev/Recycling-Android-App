package com.example.recycleme;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;


import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleme.cart.Cart;
import com.example.recycleme.adapter.CartViewAdapter;
import com.example.recycleme.cart.UserTree;
import com.example.recycleme.model.RecycledItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//Julius Liem + Harrison Black
public class CartActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private ConcatAdapter concatAdapter;
    private Cart itemsCart;
    private List<RecyclerView.Adapter<RecyclerView.ViewHolder>> adapters;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_cart, contentFrameLayout);

        this.itemsCart = Cart.getInstance();

        this.recyclerView = findViewById(R.id.itemsCartRecyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.setAdapters();

        this.saveButton = findViewById(R.id.save_button_cart);

        saveButton.setOnClickListener((click) -> {
            UserTree tree = UserTree.getInstance();

            if (itemsCart.getItems().isEmpty()) {
                Toast.makeText(getApplicationContext(), "There is no item in the cart", Toast.LENGTH_SHORT);
                return;
            }

            tree.addItems(LocalDateTime.now(), new ArrayList<>(itemsCart.getItems()));
            itemsCart.clear();
            this.setAdapters();
        });
    }

    private void setAdapters() {
        Map<String, List<RecycledItem>> recycledItemMap = this.itemsCart.getItemMaterialMap();
        adapters = new ArrayList<>();

        // create new adapter for each material
        for (Map.Entry<String, List<RecycledItem>> entry: recycledItemMap.entrySet()) {
            String material = entry.getKey();
            List<RecycledItem> items = entry.getValue();

            CartViewAdapter cartViewAdapter = new CartViewAdapter(items, material);
            adapters.add(cartViewAdapter);
        }

        this.concatAdapter = new ConcatAdapter(adapters);
        recyclerView.setAdapter(concatAdapter);
    }

}