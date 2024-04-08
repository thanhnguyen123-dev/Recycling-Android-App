package com.example.recycleme.login;

import java.io.Serial;
import java.io.Serializable;

public abstract class LoginState implements Serializable {
    public abstract void login(LoginContext context, String email, String password);
    public abstract void logout(LoginContext context);
}
