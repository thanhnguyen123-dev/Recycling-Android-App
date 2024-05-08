package com.example.recycleme.login;

public abstract class LoginState {
    public interface LoginCallback {
        void onLoginSuccess();
        void onLoginFailure(String errorMessage);
    }
    public abstract void login(LoginContext context, String email, String password, AccountAction accountAction, LoginCallback loginCallback);
    public abstract void logout(LoginContext context);
}
