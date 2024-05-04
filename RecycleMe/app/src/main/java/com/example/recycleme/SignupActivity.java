package com.example.recycleme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.recycleme.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class SignupActivity extends AppCompatActivity {
//    private Button signupButton;
//    private Button loginButton;
//    private EditText editTextEmailAddress;
//    private EditText editTextPassword;
//    private EditText getEditTextConfirmPassword;
//    private FirebaseAuth firebaseAuth;
//    private FirebaseDatabase firebaseDatabase;
//    private DatabaseReference databaseReference;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//        editTextEmailAddress = findViewById(R.id.editTextTextEmailAddress2);
//        editTextPassword = findViewById(R.id.editTextTextSignupPassword);
//        getEditTextConfirmPassword = findViewById(R.id.editTextTextConfirmPassword);
//
//
//        loginButton = findViewById(R.id.loginButtonForSignup);
//        loginButton.setOnClickListener(v -> {
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent);
//        });
//
//        firebaseAuth = FirebaseAuth.getInstance();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//
//
//
//        signupButton = findViewById(R.id.signupButton);
//        signupButton.setOnClickListener(v -> {
//            String email = editTextEmailAddress.getText().toString();
//            String password = editTextPassword.getText().toString();
//            String confirmPassword = getEditTextConfirmPassword.getText().toString();
//            if (validateSignup(email, password, confirmPassword)) {
//                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            String userUid = task.getResult().getUser().getUid();
//                            databaseReference = firebaseDatabase.getReference().child("user").child(userUid);
//                            User user = new User(email, password);
//                            setUserForReference(user);
//                        }
//                    }
//                });
//            }
//        });
//
//
//    }
//
//    private boolean validateSignup(String email, String password, String confirmPassword) {
//        if (email.isEmpty() || password.isEmpty()) {
//            Toast.makeText(this, "Email or password cannot be empty", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        else if (password.length() < 6) {
//            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
//            return false;
//
//        }
//        else if (!confirmPassword.equals(password)) {
//            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
//            return false;
//        }
//        else return true;
//    }
//
//    private void setUserForReference(User user) {
//        databaseReference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Toast.makeText(SignupActivity.this, "Cannot create account", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//    }
}