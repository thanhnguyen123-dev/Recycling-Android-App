package com.example.recycleme.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleme.R;
import com.example.recycleme.model.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

/**
 * A RecyclerView adapter for displaying a list of messages in private chat.
 * This adapter binds the data from a list of Message objects to the corresponding
 * views in RecyclerView. It handles event like sending message using a send button.
 * I read the following links to understand the general structure of a custom Adapter,
 * however all the codes are written by me.
 * https://developer.android.com/develop/ui/views/layout/recyclerview
 * https://dev.to/theplebdev/implementing-a-recyclerview-in-android-with-java-1jf5
 * @author Le Thanh Nguyen - u7594144
 * */
public class MessageAdapter extends RecyclerView.Adapter {
    Context context;
    private List<Message> messages;
    private FirebaseAuth firebaseAuth;
    private static final int TYPE_SEND = 0;
    private static final int TYPE_RECEIVE = 1;

    public MessageAdapter(Context context, List<Message> messages) {
        this.context = context;
        this.messages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        firebaseAuth = FirebaseAuth.getInstance();
        Message message = messages.get(position);
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        String userId = firebaseUser.getUid();
        if (userId.equals(message.getSenderId())) {
            return TYPE_SEND;
        }
        else return TYPE_RECEIVE;
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_SEND) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sending_view_row, parent, false);
            return new SendingMessageViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.receiving_view_row, parent, false);
            return new ReceivingMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        if (holder instanceof SendingMessageViewHolder) {
            ((SendingMessageViewHolder) holder).bind(message);
        }
        else {
            ((ReceivingMessageViewHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }


    public class SendingMessageViewHolder extends RecyclerView.ViewHolder {
        private TextView messageTextView;
        private RelativeLayout relativeLayout;

        public SendingMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.sendingMessageTextView);
            relativeLayout = itemView.findViewById(R.id.sendingMessageLayout);
        }

        public void bind(Message message) {
            messageTextView.setText(message.getSendMessage());
            relativeLayout.setVisibility(View.VISIBLE);
        }

    }

    public class ReceivingMessageViewHolder extends RecyclerView.ViewHolder {
        private TextView messageTextView;
        private RelativeLayout relativeLayout;

        public ReceivingMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.receivingMessageTextView);
            relativeLayout = itemView.findViewById(R.id.receiveMessageLayout);
        }

        public void bind(Message message) {
            messageTextView.setText(message.getSendMessage());
            relativeLayout.setVisibility(View.VISIBLE);
        }
    }

}

