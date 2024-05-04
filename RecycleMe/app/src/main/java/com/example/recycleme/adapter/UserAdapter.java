package com.example.recycleme.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleme.ChatsMainActivity;
import com.example.recycleme.MainActivity;
import com.example.recycleme.R;
import com.example.recycleme.RecycledViewAdapter;
import com.example.recycleme.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context chatsMainActivity;
    List<User> users;

    public UserAdapter(Activity chatsMainActivity, List<User> users) {
        this.chatsMainActivity = chatsMainActivity;
        this.users = users;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(chatsMainActivity).inflate(R.layout.message_view_row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView usernameTextView;
        private TextView lastMessageTextView;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.username);
            lastMessageTextView = itemView.findViewById(R.id.user_last_msg);
        }
        public void bind(User user) {
            usernameTextView.setText(user.getEmail().split("@")[0]);
            lastMessageTextView.setText(user.getLastMessage());
        }

    }
}
