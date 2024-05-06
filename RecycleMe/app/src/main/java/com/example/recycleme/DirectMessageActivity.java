package com.example.recycleme;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recycleme.adapter.MessageAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.recycleme.model.Message;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private RecyclerView messagesRecyclerView;
    private MessageAdapter messageAdapter;
    private List<Message> messages;



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

        messages = new ArrayList<>();
        messagesRecyclerView = findViewById(R.id.message_recylerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        linearLayoutManager.setStackFr(true);
        messagesRecyclerView.setLayoutManager(linearLayoutManager);

        messageAdapter = new MessageAdapter(DirectMessageActivity.this, messages);
        messagesRecyclerView.setAdapter(messageAdapter);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();


        String receiverUsername = getIntent().getStringExtra("USERNAME");
        String receiverUid = getIntent().getStringExtra("USER_ID");
        String senderUid = firebaseAuth.getUid();

        receiverNameEditText.setText(receiverUsername);

        String sendingId = senderUid + receiverUid;
        String receivingId = receiverUid + senderUid;

        usersReference = firebaseDatabase.getReference().child("users").child(senderUid);
        chatsReference = firebaseDatabase.getReference().child("chats").child(sendingId).child("messages");

        chatsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Message message = dataSnapshot.getValue(Message.class);
                    messages.add(message);
                }
                messageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSendMessage = messageEditText.getText().toString();
                if (toSendMessage.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Message cannot be empty", Toast.LENGTH_SHORT).show();

                }
                else {
                    messageEditText.setText("");
                    Date currentDate = new Date();
                    long currentTime = currentDate.getTime();
                    Message message = new Message(senderUid, currentTime, toSendMessage);
                    updateMessagesFirebaseReference(firebaseDatabase, message, receivingId, sendingId);
                }
            }
        });
    }

    private void updateMessagesFirebaseReference(FirebaseDatabase firebaseDatabase, Message message, String receivingId, String sendingId) {
        firebaseDatabase.getReference().child("chats")
                        .child(sendingId)
                        .child("messages")
                        .push().setValue(message).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        firebaseDatabase.getReference()
                                        .child("chats")
                                        .child(receivingId)
                                        .child("messages")
                                        .push().setValue(message).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                    }
                                });

                    }
                });
    }
}

