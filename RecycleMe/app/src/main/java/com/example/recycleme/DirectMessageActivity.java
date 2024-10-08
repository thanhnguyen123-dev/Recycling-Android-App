package com.example.recycleme;

import android.os.Bundle;
import android.provider.ContactsContract;
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
import com.example.recycleme.util.UserProfileUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import com.example.recycleme.model.Message;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * An activity to chat with other user
 * I got an overview idea of how to implement private chat using the below link
 * Source: <a href="https://medium.com/@rajsalunkhe8055/building-a-firebase-chat-app-in-android-in-depth-guide-31a52b2681a8">...</a>
 * @author Le Thanh Nguyen - u7594144
 * */
public class DirectMessageActivity extends BaseActivity {
    private ImageView receiverProfilePic;
    private CardView sendButton;
    private TextView receiverNameEditText;
    private EditText messageEditText;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference chatsReference;
    private RecyclerView messagesRecyclerView;
    private MessageAdapter messageAdapter;
    private List<Message> messages;
    private String chatId;
    private static final int STACK_FROM_END_THRESHOLD = 4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_direct_message, contentFrameLayout);

        sendButton = findViewById(R.id.send_button);
        receiverNameEditText = findViewById(R.id.receiver_user);
        messageEditText = findViewById(R.id.input_message);

        messages = new ArrayList<>();
        messagesRecyclerView = findViewById(R.id.message_recylerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        updateLayoutManager(linearLayoutManager);

        messagesRecyclerView.setLayoutManager(linearLayoutManager);

        messageAdapter = new MessageAdapter(DirectMessageActivity.this, messages);
        messagesRecyclerView.setAdapter(messageAdapter);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        String receiverUsername = getIntent().getStringExtra("USERNAME");
        String receiverUid = getIntent().getStringExtra("USER_ID");
        String senderUid = firebaseAuth.getUid();
        receiverProfilePic = findViewById(R.id.receiver_profile_pic);
        UserProfileUtil.retrieveUserImage(receiverUid, getApplicationContext(), receiverProfilePic);

        receiverNameEditText.setText(receiverUsername);

        chatId = getChatId(senderUid, receiverUid);

        chatsReference = firebaseDatabase.getReference().child("chats").child(chatId).child("messages");

        chatsReference.orderByChild("sendTime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messages.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Message message = dataSnapshot.getValue(Message.class);
                    messages.add(message);
                }
                messageAdapter.notifyDataSetChanged();
                if (!messages.isEmpty()) {
                    messagesRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            messagesRecyclerView.smoothScrollToPosition(messages.size() - 1);
                        }
                    });
                }
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
                    updateMessagesFirebaseReference(firebaseDatabase, senderUid, currentTime, toSendMessage);
                }
            }
        });
    }

    private void updateMessagesFirebaseReference(FirebaseDatabase firebaseDatabase, String senderId, long sendTime, String toSendMessage) {
        Message message = new Message(senderId, sendTime, toSendMessage);
        firebaseDatabase.getReference().child("chats")
                        .child(chatId)
                        .child("messages")
                        .push().setValue(message).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Failed to send message", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private String getChatId(String senderId, String receiverId) {
        if (senderId == null || receiverId == null) return "";
        if (senderId.hashCode() < receiverId.hashCode()) {
            return senderId + receiverId;
        }
        else return receiverId + senderId;
    }

    private void updateLayoutManager(LinearLayoutManager linearLayoutManager) {
        if (linearLayoutManager != null) {
            linearLayoutManager.setStackFromEnd(messages.size() > STACK_FROM_END_THRESHOLD);
        }
    }
}

