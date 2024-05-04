package com.example.recycleme.login;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;

public class LoginContext {
    private LoginState state;
    private static LoginContext instance;
    private FirebaseAuth mAuth;
    private String userEmail;

    private LoginContext() {
        state = new LoggedOutState();
        mAuth = FirebaseAuth.getInstance();
    }

    public static LoginContext getInstance() {
        if (instance == null) {
            synchronized (LoginContext.class) {
                if (instance == null) {
                    instance = new LoginContext();
                }
            }
        }

        return instance;
    }


    public void setState(LoginState state) {
        this.state = state;
    }

    public void login(String email, String password, AccountAction accountAction, LoginState.LoginCallback loginCallback) {
        state.login(this, email, password, accountAction, loginCallback);
    }

    public void logout() {
        state.logout(this);
    }

    public boolean isLoggedIn() {
        return state instanceof LoggedInState;
    }

    public FirebaseAuth getFireBaseAuth() {
        return mAuth;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
