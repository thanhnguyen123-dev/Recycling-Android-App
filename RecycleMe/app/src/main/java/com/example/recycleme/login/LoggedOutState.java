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
import com.google.firebase.auth.FirebaseUser;

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
            firebaseAuth.createUserWithEmailAndPassword(email, password)
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
    }

    @Override
    public void logout(LoginContext context) {
        System.out.println("Already logged out.");
    }
}
