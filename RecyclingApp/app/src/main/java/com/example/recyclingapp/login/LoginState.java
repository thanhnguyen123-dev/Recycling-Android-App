package com.example.recyclingapp.login;

import com.example.recyclingapp.login.LoginContext;

public abstract class LoginState {
    public abstract void login(LoginContext context, String email, String password);
    public abstract void logout(LoginContext context);
}
