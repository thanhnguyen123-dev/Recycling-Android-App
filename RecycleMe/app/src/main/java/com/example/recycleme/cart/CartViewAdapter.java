package com.example.recycleme.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleme.*;
import com.example.recycleme.RecycledItem;

import org.w3c.dom.Text;

import java.util.List;

public class CartViewAdapter extends RecyclerView.Adapter<CartViewAdapter.ViewHolder> {

    private List<RecycledItem> cartItems;
    public List<RecycledItem> getCartItems() { return this.cartItems;}

    public void setCartItems(List<RecycledItem> cartItems) {
        this.cartItems = cartItems;
    }

    public CartViewAdapter(List<RecycledItem> cartItems) {
        this.cartItems = ItemsCart.getInstance().getItems();
    }

    @NonNull
    @Override
    public CartViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_view_row, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewAdapter.ViewHolder holder, int position) {
        holder.bind(this.cartItems.get(position));
    }

    @Override
    public int getItemCount() {return this.cartItems.size();}

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;
        private TextView brandNameTextView;
        private TextView materialTextView;
        private TextView valueTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.productNameTextView = itemView.findViewById(R.id.product_name_cart);
            this.brandNameTextView = itemView.findViewById(R.id.product_brand_text_cart);
            this.materialTextView = itemView.findViewById(R.id.product_material_text_cart);
            this.valueTextView = itemView.findViewById(R.id.value_text_cart);
        }

        public void bind(RecycledItem item) {
            productNameTextView.setText(item.getItem());
            brandNameTextView.setText(item.getBrandName());
            materialTextView.setText(item.getMaterial());
            valueTextView.setText(String.valueOf(item.getValue()));
        }
    }
}
