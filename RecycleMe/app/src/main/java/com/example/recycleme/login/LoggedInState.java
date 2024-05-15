package com.example.recycleme.login;

import android.content.Intent;

import com.example.recycleme.cart.Cart;
import com.example.recycleme.cart.UserTree;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * One of the state for State design pattern, the other one is LoggedOutState
 * @author Julius Liem - u7724204
 * @author Le Thanh Nguyen - u7594144 (for the Firebase part)
 */

public class LoggedInState extends LoginState {
    @Override
    public void login(LoginContext context, String email, String password, AccountAction accountAction, LoginCallback loginCallback) {
        System.out.println("Already logged in.");
    }

    @Override
    public void logout(LoginContext context) {
        /**
         * Written by Le Thanh Nguyen
         */
        FirebaseAuth firebaseAuth = context.getFireBaseAuth();
        firebaseAuth.signOut();

        // written by Julius Liem
        UserTree.getInstance().clear();
        UserTree.getInstance().simulatePreviousAddition();
        Cart.getInstance().clear();
        context.setState(new LoggedOutState());
    }
}
