package com.example.recycleme.login;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;

public class LoginContext {
    private LoginState state;
    private static LoginContext instance;
    private static FirebaseAuth firebaseAuth;

    private LoginContext() {
        state = new LoggedOutState();
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

    public void login(String email, String password) {
        state.login(this, email, password);
    }

    public void logout() {
        state.logout(this);
    }

    public boolean isLoggedIn() {
        return state instanceof LoggedInState;
    }
}
