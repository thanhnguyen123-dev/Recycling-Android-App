package com.example.recycleme;

import android.os.Bundle;
import android.widget.Toast;

public class ChatsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chats);
        Toast.makeText(getApplicationContext(), "chat activity", Toast.LENGTH_SHORT).show();
    }
}