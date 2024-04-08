package com.example.recycleme.login;

public class LoggedInState extends LoginState {
    @Override
    public void login(LoginContext context, String email, String password) {
        System.out.println("Already logged in.");
    }

    @Override
    public void logout(LoginContext context) {
        context.setState(new LoggedOutState());
    }
}
