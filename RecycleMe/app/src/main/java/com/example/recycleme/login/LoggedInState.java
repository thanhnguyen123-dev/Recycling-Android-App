package com.example.recycleme.login;

import android.content.Intent;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoggedInState extends LoginState {
    @Override
    public void login(LoginContext context, String email, String password) {
        System.out.println("Already logged in.");
    }

    @Override
    public void logout(LoginContext context) {
        FirebaseAuth firebaseAuth = context.getFireBaseAuth();
        firebaseAuth.signOut();
        context.setState(new LoggedOutState());
    }
}
