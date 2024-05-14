package com.example.recycleme.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.recycleme.MainActivity;
import com.example.recycleme.ProfileActivity;
import com.example.recycleme.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * LoggedOutState denotes the current state of LoginState.
 * It has login method to allow logged out users to login.
 * I used Claude AI to give me hints on how to deal with the delay for logging in,
 * which was done by introducing a LoginCallback argument. However, all of the codes are written by me.
 * @author Le Thanh Nguyen - u7594144
 */
public class LoggedOutState extends LoginState {
    @Override
    public void login(LoginContext context, String email, String password,  AccountAction accountAction, LoginCallback loginCallback) {
        FirebaseAuth firebaseAuth = context.getFireBaseAuth();
        if (accountAction == AccountAction.LOGIN_ACTION) {
            firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            context.setState(new LoggedInState());
                            loginCallback.onLoginSuccess();
                        } else {
                            loginCallback.onLoginFailure(task.getException().getMessage());
                        }
                    }
                });
        }
        else if (accountAction == AccountAction.SIGNUP_ACTION){
            createUserFirebaseAuthorize(context, email, password, loginCallback);
        }
    }

    @Override
    public void logout(LoginContext context) {
        System.out.println("Already logged out.");
    }


    private void createUserFirebaseAuthorize(LoginContext context, String email, String password, LoginCallback loginCallback) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = task.getResult().getUser().getUid();
                    User user = new User(userId, email, password);
                    setUserReference(context, userId, user, loginCallback);
                }
            }
        });

    }

    private void setUserReference(LoginContext context, String userId, User user, LoginCallback loginCallback) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("users").child(userId);
        databaseReference.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    context.setState(new LoggedInState());
                    loginCallback.onLoginSuccess();
                } else {
                    loginCallback.onLoginFailure(task.getException().getMessage());
                }
            }
        });
    }







}
