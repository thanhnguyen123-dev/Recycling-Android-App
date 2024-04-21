package com.example.recycleme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleme.cart.ItemsCart;

import java.util.List;

public class RecycledViewAdapter extends RecyclerView.Adapter<RecycledViewAdapter.ViewHolder>{

    public List<RecycledItem> getRecycledItems() {
        return recycledItems;
    }

    public void setRecycledItems(List<RecycledItem> recycledItems) {
        this.recycledItems = recycledItems;
    }

    private List<RecycledItem> recycledItems;

    public RecycledViewAdapter(List<RecycledItem> recycledItems) {
        this.recycledItems = recycledItems;
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
        private Button addButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.productNameTextView = itemView.findViewById(R.id.product_name_text);
            this.brandNameTextView = itemView.findViewById(R.id.product_brand_text);
            this.materialTextView = itemView.findViewById(R.id.product_material_text);
            this.valueTextView = itemView.findViewById(R.id.product_value_text);
            this.addButton = itemView.findViewById(R.id.add_button);

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        RecycledItem item = recycledItems.get(position);
                        addItemToCart(item);
                        Toast.makeText(itemView.getContext(), "Added one item to the cart", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        public void bind(RecycledItem item) {
            productNameTextView.setText(item.getItem());
            brandNameTextView.setText(item.getBrandName());
            materialTextView.setText(item.getMaterial());
            valueTextView.setText(String.valueOf(item.getValue()));
        }

        private void addItemToCart(RecycledItem item) {
            ItemsCart cart = ItemsCart.getInstance();
            cart.addItem(item);
        }
    }
}
