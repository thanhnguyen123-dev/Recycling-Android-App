package com.example.recycleme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleme.R;
import com.example.recycleme.RecycledItem;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * RecyclerView adapter for displaying a list of recycled items with a header showing the date.
 * <p>
 * This adapter handles two view types: a header view for displaying the date and item views for displaying the recycled items.
 * The adapter binds the data to the corresponding views in the onBindViewHolder(RecyclerView.ViewHolder, int) method.
 * <p>
 * The implementation of this adapter was created with the help of generative AI, specifically for the
 * onCreateViewHolder(ViewGroup, int) and onBindViewHolder(RecyclerView.ViewHolder, int) methods.
 * The generative AI assisted in generating the code for handling the different view types and binding the data to the views.
 * <p>
 * Additionally, this adapter implementation references and incorporates concepts from the following Stack Overflow answer:
 * <a href="https://stackoverflow.com/questions/64216153/header-in-recycler-view-what-is-the-best-practice-in-2020">
 * Header in RecyclerView - What is the best practice in 2020?</a>
 * The answer provided guidance on using ConcatAdapter to implement header for adapter.
 *
 * @author Julius Liem
 */
public class RecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private LocalDateTime date;
    private List<RecycledItem> items;
    public RecordAdapter(LocalDateTime date, List<RecycledItem> items) {
        this.date = date;
        this.items = items;
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
           View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_date_cart, parent, false);
           return new HeaderViewHolder(view);
       } else {
           View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_view_row, parent, false);
           return new ItemViewHolder(view);
       }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            headerViewHolder.bind(date);
        } else {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            RecycledItem item = items.get(position - 1);
            itemViewHolder.bind(item);
        }
    }

    @Override
    public int getItemCount() {
        // Accounting for the header and the actual recycled items
        return items.size() + 1;
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {

        private TextView dateTextView;
        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            this.dateTextView = itemView.findViewById(R.id.date_header);
        }

        public void bind(LocalDateTime date) {
            dateTextView.setText(date.format(DateTimeFormatter.ISO_DATE));
        }
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView productNameTextView;
        private TextView brandNameTextView;
        private TextView materialTextView;
        private TextView valueTextView;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.productNameTextView = itemView.findViewById(R.id.product_name_record);
            this.brandNameTextView = itemView.findViewById(R.id.product_brand_text_record);
            this.materialTextView = itemView.findViewById(R.id.product_material_text_record);
            this.valueTextView = itemView.findViewById(R.id.value_text_record);
        }

        public void bind(RecycledItem item) {
            productNameTextView.setText(item.getItem());
            brandNameTextView.setText(item.getBrandName());
            materialTextView.setText(item.getMaterial());
            valueTextView.setText(String.valueOf(item.getValue()));
        }
    }
}
