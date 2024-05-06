package com.example.recycleme;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DirectMessageActivity extends BaseActivity {
    private ImageView userImage;
    private CardView sendButton;
    private ImageButton backButton;
    private TextView receiverNameEditText;
    private EditText messageEditText;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference usersReference;
    private DatabaseReference chatsReference;
    private String sendingId, receivingId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_direct_message, contentFrameLayout);

        userImage = findViewById(R.id.user_img);
        sendButton = findViewById(R.id.send_button);
        backButton = findViewById(R.id.msg_back_button);
        receiverNameEditText = findViewById(R.id.receiver_user);
        messageEditText = findViewById(R.id.input_message);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        String receiverUsername = getIntent().getStringExtra("USERNAME");
        String receiverUid = getIntent().getStringExtra("USER_ID");
        String senderUid = firebaseAuth.getUid();

        String sendingId = (new StringBuffer("")).append(senderUid).append(receiverUid).toString();
        String receivingId = (new StringBuffer("")).append(receiverUid).append(senderUid).toString();



    }
}

