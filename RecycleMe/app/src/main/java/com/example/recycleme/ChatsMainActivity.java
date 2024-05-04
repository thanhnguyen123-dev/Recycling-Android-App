package com.example.recycleme;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleme.adapter.UserAdapter;
import com.google.firebase.auth.FirebaseAuth;

public class ChatsMainActivity extends BaseActivity {
    private FirebaseAuth firebaseAuth;
    private RecyclerView userRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_chats_main, contentFrameLayout);

        firebaseAuth = FirebaseAuth.getInstance();
        userRecyclerView = findViewById(R.id.chats_recyclerview);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


    }


}