package com.example.recycleme.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleme.ChatProfileViewActivity;
import com.example.recycleme.DirectMessageActivity;
import com.example.recycleme.R;
import com.example.recycleme.model.User;
import com.example.recycleme.util.LogUtil;
import com.example.recycleme.util.UserProfileUtil;

import java.util.List;

/**
 * https://developer.android.com/develop/ui/views/layout/recyclerview
 * https://dev.to/theplebdev/implementing-a-recyclerview-in-android-with-java-1jf5
 * */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context chatsMainActivity;
    private List<User> users;

    public UserAdapter(Activity chatsMainActivity, List<User> users) {
        this.chatsMainActivity = chatsMainActivity;
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_view_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.bind(user);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(chatsMainActivity, DirectMessageActivity.class);
            intent.putExtra("USERNAME", LogUtil.getUsernameFromEmail(user.getEmail()));
            intent.putExtra("USER_ID", user.getId());
            chatsMainActivity.startActivity(intent);
        });

    }



    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView usernameTextView;
        private ImageView userProfileImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            usernameTextView = itemView.findViewById(R.id.username);
            userProfileImageView = itemView.findViewById(R.id.user_profile_img);
        }
        public void bind(User user) {
            usernameTextView.setText(LogUtil.getUsernameFromEmail(user.getEmail()));
            UserProfileUtil.retrieveUserImage(user.getId(), itemView.getContext(), userProfileImageView);
            userProfileImageView.setOnClickListener(v -> {
                Intent intent = new Intent(chatsMainActivity, ChatProfileViewActivity.class);
                intent.putExtra("USER_NAME", LogUtil.getUsernameFromEmail(user.getEmail()));
                intent.putExtra("USER_ID", user.getId());
                chatsMainActivity.startActivity(intent);
            });
        }

    }
}

