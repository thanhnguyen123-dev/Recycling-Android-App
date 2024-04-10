package com.example.recycleme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycledViewAdapter extends RecyclerView.Adapter<RecycledViewAdapter.ViewHolder>{

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.productNameTextView = itemView.findViewById(R.id.product_name_text);
            this.brandNameTextView = itemView.findViewById(R.id.product_brand_text);
            this.materialTextView = itemView.findViewById(R.id.product_material_text);
            this.valueTextView = itemView.findViewById(R.id.product_value_text);
        }

        public void bind(RecycledItem item) {
            productNameTextView.setText(item.getItem());
            brandNameTextView.setText(item.getBrandName());
            materialTextView.setText(item.getMaterial());
            valueTextView.setText(String.valueOf(item.getValue()));
        }
    }
}
