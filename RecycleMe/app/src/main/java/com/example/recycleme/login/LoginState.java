package com.example.recycleme.login;

import java.io.Serial;
import java.io.Serializable;

public abstract class LoginState {
    public interface LoginCallback {
        void onLoginSuccess();
        void onLoginFailure(String errorMessage);
    }
    public abstract void login(LoginContext context, String email, String password, LoginCallback loginCallback);
    public abstract void logout(LoginContext context);
}
