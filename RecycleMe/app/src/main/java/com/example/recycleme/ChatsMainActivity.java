package com.example.recycleme;

import android.os.Bundle;
import android.widget.FrameLayout;

public class ChatsMainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_chats_main, contentFrameLayout);
    }


}