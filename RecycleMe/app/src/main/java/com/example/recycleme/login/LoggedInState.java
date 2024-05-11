package com.example.recycleme.login;

import android.content.Intent;

import com.example.recycleme.cart.Cart;
import com.example.recycleme.cart.UserTree;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoggedInState extends LoginState {
    @Override
    public void login(LoginContext context, String email, String password, AccountAction accountAction, LoginCallback loginCallback) {
        System.out.println("Already logged in.");
    }

    @Override
    public void logout(LoginContext context) {
        FirebaseAuth firebaseAuth = context.getFireBaseAuth();
        firebaseAuth.signOut();
        UserTree.getInstance().clear();
        Cart.getInstance().clear();
        context.setState(new LoggedOutState());
    }
}
