package com.example.recycleme;

import org.junit.Before;
import org.junit.Test;

import com.example.recycleme.login.LoggedInState;
import com.example.recycleme.login.LoggedOutState;
import com.example.recycleme.login.LoginContext;
import com.example.recycleme.login.LoginState;

import static org.junit.Assert.*;

public class LoginTests {

    private LoginContext loginContext;

    @Before
    public void setUp() {
        loginContext = LoginContext.getInstance();
    }

    @Test
    public void testLoginAndLogout() {
        assertFalse(loginContext.isLoggedIn());

        // Attempt to log in with invalid credentials
        loginContext.login("invalid@example.com", "password", new LoginState.LoginCallback() {
            @Override
            public void onLoginSuccess() {
                fail();
            }

            @Override
            public void onLoginFailure(String errorMessage) {
                assertFalse(loginContext.isLoggedIn());
            }
        });

        // Log in with valid credentials
        loginContext.login("comp2100@anu.edu.au", "comp2100", new LoginState.LoginCallback() {
            @Override
            public void onLoginSuccess() {
                assertTrue(loginContext.isLoggedIn());
            }

            @Override
            public void onLoginFailure(String errorMessage) {
                fail();
            }
        });

        // Attempt to log in while already logged in
        loginContext.login("anotheruser@example.com", "password", new LoginState.LoginCallback() {
            @Override
            public void onLoginSuccess() {
                assertTrue(loginContext.isLoggedIn());
            }

            @Override
            public void onLoginFailure(String errorMessage) {
                fail();
            }
        });


        // Log out
        loginContext.logout();
        assertFalse(loginContext.isLoggedIn());

        // Attempt to log out while already logged out
        loginContext.logout();
        assertFalse(loginContext.isLoggedIn());
    }

    @Test
    public void testLoggedInState() {
        LoginState loggedInState = new LoggedInState();
        assertFalse(loggedInState instanceof LoggedOutState);

        LoginContext context = LoginContext.getInstance();
        assertFalse(context.isLoggedIn());

        // Set the context to the LoggedInState
        context.setState(loggedInState);
        assertTrue(context.isLoggedIn());

        // Attempt to log in while already logged in
        context.login("comp2100@anu.edu.au", "comp2100", new LoginState.LoginCallback() {
            @Override
            public void onLoginSuccess() {
                assertTrue(context.isLoggedIn());
            }

            @Override
            public void onLoginFailure(String errorMessage) {
                fail();
            }
        });


        // Log out
        context.logout();
        assertFalse(context.isLoggedIn());
    }

    @Test
    public void testLoggedOutState() {
        LoginState loggedOutState = new LoggedOutState();
        assertFalse(loggedOutState instanceof LoggedInState);

        LoginContext context = LoginContext.getInstance();
        assertFalse(context.isLoggedIn());

        // Set the context to the LoggedOutState
        context.setState(loggedOutState);
        assertFalse(context.isLoggedIn());

        // Log in with valid credentials
        context.login("comp2100@anu.edu.au", "comp2100", new LoginState.LoginCallback() {
            @Override
            public void onLoginSuccess() {
                assertTrue(context.isLoggedIn());
            }

            @Override
            public void onLoginFailure(String errorMessage) {
                fail();
            }
        });

        // Log out
        context.logout();
        assertFalse(context.isLoggedIn());
    }
}
