package com.example.recycleme;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleme.cart.Cart;

import java.util.List;

/**
 * A RecyclerView adapter for displaying a list of recycled items.
 * <p>
 * This adapter binds the data from a list of RecycledItem objects to the corresponding
 * views in the RecyclerView. It also handles the click event for adding items to the cart.
 * </p>
 * <p>
 * Adapted from the YouTube video "RecyclerView | Everything You Need to Know"
 * (https://www.youtube.com/watch?v=Mc0XT58A1Z4).
 * </p>
 * @author Julius Liem
 */
public class RecycledViewAdapter extends RecyclerView.Adapter<RecycledViewAdapter.ViewHolder>{

    public List<RecycledItem> getRecycledItems() {
        return recycledItems;
    }

    public void setRecycledItems(List<RecycledItem> recycledItems) {
        this.recycledItems = recycledItems;
    }

    private List<RecycledItem> recycledItems;

    public Cart cart;

    public RecycledViewAdapter(List<RecycledItem> recycledItems) {
        this.recycledItems = recycledItems;
        this.cart = Cart.getInstance();
    }
    @NonNull
    @Override
    public RecycledViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycledViewAdapter.ViewHolder holder, int position) {
        holder.bind(this.recycledItems.get(position));
    }

    @Override
    public int getItemCount() {
        return this.recycledItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;
        private TextView brandNameTextView;
        private TextView materialTextView;
        private TextView valueTextView;

        private Button addToCartButton;
        private ImageView itemDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.productNameTextView = itemView.findViewById(R.id.product_name_text);
            this.brandNameTextView = itemView.findViewById(R.id.product_brand_text);
            this.materialTextView = itemView.findViewById(R.id.product_material_text);
            this.valueTextView = itemView.findViewById(R.id.product_value_text);
            this.addToCartButton = itemView.findViewById(R.id.add_cart_button);
            this.itemDescription = itemView.findViewById(R.id.itemDescription);
        }

        public void bind(RecycledItem item) {
            productNameTextView.setText(item.getItem());
            brandNameTextView.setText(item.getBrandName());
            materialTextView.setText(item.getMaterial());
            valueTextView.setText(String.valueOf(item.getValue()));
            addToCartButton.setOnClickListener(v -> {
                cart.addItem(item);
                Toast.makeText(v.getContext(), "Item added to cart", Toast.LENGTH_SHORT).show();
            });
            addToCartButton.setOnClickListener(v -> {
                Toast.makeText(v.getContext(), "Description", Toast.LENGTH_SHORT).show();
            });
            itemDescription.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), ItemDescriptionActivity.class);
                v.getContext().startActivity(intent);
            });
        }
    }
}
