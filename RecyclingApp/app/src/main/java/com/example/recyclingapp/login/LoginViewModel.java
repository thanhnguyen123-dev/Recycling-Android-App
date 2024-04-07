package com.example.recyclingapp.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {
    private LoginContext loginContext;

    public LoginViewModel() {
        this.loginContext = LoginContext.getInstance();
    }

    // getter and setter
    public LoginContext getLoginContext() {
        return loginContext;
    }

    public void login(String email, String password) {
        loginContext.login(email, password);
        boolean result = loginContext.isLoggedIn();
    }

    public boolean isLoggedIn() {
        return loginContext.isLoggedIn();
    }


}
