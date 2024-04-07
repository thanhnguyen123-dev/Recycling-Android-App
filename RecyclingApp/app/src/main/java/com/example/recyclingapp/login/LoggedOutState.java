package com.example.recyclingapp.login;

public class LoggedOutState extends LoginState {

    @Override
    public void login(LoginContext context, String email, String password) {
//        perform login logic
//        placeholder (will probably be changed w/ some hashing afterwards)

        if (email.equals("comp2100@anu.edu.au") && password.equals("comp2100") || email.equals("comp6400@anu.edu.au") && password.equals("comp6442")) {
            context.setState(new LoggedInState());
        }
    }

    @Override
    public void logout(LoginContext context) {
        System.out.println("Already logged out.");
    }
}
