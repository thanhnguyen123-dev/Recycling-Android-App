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
import com.example.recycleme.login.LoginContext;
import com.example.recycleme.login.LoginState;
import com.example.recycleme.util.LogUtil;

/**
 * A fragment for new user to create new account with user information.
 * @author Le Thanh Nguyen - u7594144
 */
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
            if (LogUtil.validateSignup(getContext(), email, password, confirmPassword)) {
                loginContext.login(email, password, AccountAction.SIGNUP_ACTION, new LoginState.LoginCallback() {
                    @Override
                    public void onLoginSuccess() {
                        loginContext.setUserEmail(email);
                        updateUI();
                    }

                    @Override
                    public void onLoginFailure(String errorMessage) {
                        Toast.makeText(getContext(), "Cannot sign up", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });


        return builder.create();
    }



    private void updateUI() {
        if (loginContext.isLoggedIn()) {
            Intent intent = new Intent(getContext(), ProfileActivity.class);
            startActivity(intent);
            Toast.makeText(getContext(), "Signed up successfully", Toast.LENGTH_SHORT).show();
        } else Toast.makeText(getContext(), "Cannot sign up", Toast.LENGTH_SHORT).show();
    }


}

