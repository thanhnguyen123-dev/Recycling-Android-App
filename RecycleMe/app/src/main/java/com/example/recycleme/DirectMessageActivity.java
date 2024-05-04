package com.example.recycleme;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class DirectMessageActivity extends BaseActivity {
    private ImageView userImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_direct_message, contentFrameLayout);

        userImage = findViewById(R.id.user_img);

    }
}