package com.example.recycleme;

import androidx.fragment.app.DialogFragment;
import androidx.annotation.NonNull;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.time.ZoneId;


public class SignupFragment extends DialogFragment {
    private EditText emailAddressEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button registerButton;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_signup, null);
        builder.setView(view)
                .setNegativeButton("Cancel", (dialog, which) -> dismiss());


        String email = emailAddressEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        registerButton = view.findViewById(R.id.register_button);

        registerButton.setOnClickListener(v -> {
            emailAddressEditText = view.findViewById(R.id.email_signup);
            passwordEditText = view.findViewById(R.id.pass_signup);
            confirmPasswordEditText = view.findViewById(R.id.cfm_pass_signup);

        });









        return builder.create();
    }
}

