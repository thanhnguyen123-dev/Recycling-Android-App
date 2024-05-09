package com.example.recycleme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleme.*;
import com.example.recycleme.model.RecycledItem;
import com.example.recycleme.cart.Cart;

import java.util.List;

/**
 * RecyclerView adapter for displaying a list of recycled items with a header showing the materials.
 * <p>
 * This adapter handles two view types: a header view for displaying the date and item views for displaying the recycled items.
 * The adapter binds the data to the corresponding views in the onBindViewHolder(RecyclerView.ViewHolder, int) method.
 * <p>
 * The implementation of this adapter was created by modifying the code snippets from another class (RecordAdapter), which was
 * written using the help of GenerativeAI.
 * <p>
 * Additionally, this adapter implementation references and incorporates concepts from the following Stack Overflow answer:
 * <a href="https://stackoverflow.com/questions/64216153/header-in-recycler-view-what-is-the-best-practice-in-2020">
 * Header in RecyclerView - What is the best practice in 2020?</a>
 * The answer provided guidance on using ConcatAdapter to implement header for adapter.
 *
 * @author Julius Liem
 */
public class CartViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String material;
    private List<RecycledItem> cartItems;
    public List<RecycledItem> getCartItems() { return this.cartItems;}

    public void setCartItems(List<RecycledItem> cartItems) {
        this.cartItems = cartItems;
    }

    public CartViewAdapter(List<RecycledItem> cartItems, String material) {
        this.cartItems = cartItems;
        this.material = material;
    }

    public boolean isListEmpty() {
        return cartItems.isEmpty();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return TYPE_HEADER;
        return TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_cart, parent, false);
            return new HeaderViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_view_row, parent, false);
            return new ItemViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.bind(material);
        } else {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            RecycledItem item = cartItems.get(position - 1);
            itemViewHolder.bind(item);
        }
    }

    @Override
    public int getItemCount() {
        return this.cartItems.size() + 1;
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView materialTextView;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.materialTextView = itemView.findViewById(R.id.item_type_header);
        }

        public void bind(String material) {
            this.materialTextView.setText(material);
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;
        private TextView brandNameTextView;
        private TextView materialTextView;
        private TextView valueTextView;
        private Button delButton;
        private ImageButton descriptionButton;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.productNameTextView = itemView.findViewById(R.id.product_name_cart);
            this.brandNameTextView = itemView.findViewById(R.id.product_brand_text_cart);
            this.materialTextView = itemView.findViewById(R.id.product_material_text_cart);
            this.valueTextView = itemView.findViewById(R.id.value_text_cart);
            this.delButton = itemView.findViewById(R.id.del_button);
        }

        public void bind(RecycledItem item) {
            productNameTextView.setText(item.getItem());
            brandNameTextView.setText(item.getBrandName());
            materialTextView.setText(item.getMaterial());
            valueTextView.setText(String.valueOf(item.getValue()));

            delButton.setOnClickListener(v -> {
                Cart.getInstance().removeItem(item);
                getBindingAdapter().notifyDataSetChanged();

                // if the header is the only one left
                // remove the header
                if (getBindingAdapter().getItemCount() == 1) {
                    getBindingAdapter().notifyItemRangeRemoved(0, 1);
                }

            });
        }
    }
}
