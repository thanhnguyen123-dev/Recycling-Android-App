package com.example.recycleme.login;

import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.recycleme.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoggedOutState extends LoginState {

    @Override
    public void login(LoginContext context, String email, String password) {
        FirebaseAuth firebaseAuth = context.getFireBaseAuth();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            context.setState(new LoggedInState());
                        }
                    }
                });
    }

    @Override
    public void logout(LoginContext context) {
        System.out.println("Already logged out.");
    }
}
