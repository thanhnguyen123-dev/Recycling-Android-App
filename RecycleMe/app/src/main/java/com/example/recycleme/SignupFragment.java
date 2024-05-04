package com.example.recycleme;

import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recycleme.login.AccountAction;
import com.example.recycleme.login.LoggedInState;
import com.example.recycleme.login.LoginContext;
import com.example.recycleme.login.LoginState;
import com.example.recycleme.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.time.ZoneId;


public class SignupFragment extends DialogFragment {
    private EditText emailAddressEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button registerButton;
    private LoginContext loginContext;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_signup, null);
        builder.setView(view)
                .setNegativeButton("Cancel", (dialog, which) -> dismiss());

        emailAddressEditText = view.findViewById(R.id.email_signup);
        passwordEditText = view.findViewById(R.id.pass_signup);
        confirmPasswordEditText = view.findViewById(R.id.cfm_pass_signup);

        loginContext = LoginContext.getInstance();
        registerButton = view.findViewById(R.id.register_button);
        registerButton.setOnClickListener(v -> {
            String email = emailAddressEditText.getText().toString();
            String password = passwordEditText.getText().toString();
            String confirmPassword = confirmPasswordEditText.getText().toString();
            if (validateSignup(email, password, confirmPassword)) {
                loginContext.login(email, password, AccountAction.SIGNUP_ACTION, new LoginState.LoginCallback() {
                    @Override
                    public void onLoginSuccess() {
                        updateUI();
                    }

                    @Override
                    public void onLoginFailure(String errorMessage) {
                        if (email.isEmpty() || password.isEmpty()) {
                            Toast.makeText(getContext(), "Email and password cannot be empty", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


        return builder.create();
    }


    private boolean validateSignup(String email, String password, String confirmPassword) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(getContext(), "Email or password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (password.length() < 6) {
            Toast.makeText(getContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;

        }
        else if (!confirmPassword.equals(password)) {
            Toast.makeText(getContext(), "Password does not match", Toast.LENGTH_SHORT).show();
            return false;
        }
        else return true;
    }


    private void updateUI() {
        if (loginContext.isLoggedIn()) {
            // create  new fragment that will be displayed on screen
            Intent intent = new Intent(getContext(), ProfileActivity.class);
            startActivity(intent);
            Toast.makeText(getContext(), "Created account successfully", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(getContext(), "Username and password not recognized", Toast.LENGTH_SHORT).show();
    }





}

