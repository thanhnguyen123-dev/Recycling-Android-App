package com.example.recycleme.record;

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
