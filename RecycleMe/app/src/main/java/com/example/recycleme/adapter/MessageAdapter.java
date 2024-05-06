package com.example.recycleme.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleme.model.Message;

import java.util.List;

/**
 * https://developer.android.com/develop/ui/views/layout/recyclerview
 * https://dev.to/theplebdev/implementing-a-recyclerview-in-android-with-java-1jf5
 * */

public class MessageAdapter extends RecyclerView.Adapter {
    Context context;
    private List<Message> messages;
    private static final int TYPE_SEND = 0;
    private static final int TYPE_RECEIVE = 1;

    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        Message message = messages.get(position);

    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


}

