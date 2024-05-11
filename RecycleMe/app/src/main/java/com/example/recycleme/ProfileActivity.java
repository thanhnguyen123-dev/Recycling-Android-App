package com.example.recycleme;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recycleme.login.LoginContext;
import com.example.recycleme.util.LogUtil;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.StorageReference;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class ProfileActivity extends BaseActivity {
    private LoginContext loginContext;
    private TextView usernameTextView;
    private Button logOutButton;
    private Button uploadButton;
    private ActivityResultLauncher<Intent> imageLauncher;
    private Uri imageUri;
    private ImageView profilePicImageView;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_profile, contentFrameLayout);

        loginContext = LoginContext.getInstance();
        usernameTextView = findViewById(R.id.profile_username);
        String emailText = loginContext.getUserEmail();
        String username = LogUtil.getUsernameFromEmail(emailText);
        usernameTextView.setText(username);
        profilePicImageView = findViewById(R.id.profilePicImageView);


        logOutButton = findViewById(R.id.logout_button);
        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginContext.logout();
                updateUI();
            }
        });

        imageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                res -> {
                    if (res.getResultCode() == Activity.RESULT_OK) {
                        Intent data = res.getData();
                        if (data != null && data.getData() != null) {
                            imageUri = data.getData();
                            setProfilePic(imageUri, profilePicImageView);

                        }
                    }
                }
                );

        uploadButton = findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(v -> {
            ImagePicker.with(this).cropSquare().compress(512).maxResultSize(512, 512)
                    .createIntent(new Function1<Intent, Unit>() {
                        @Override
                        public Unit invoke(Intent intent) {
                            imageLauncher.launch(intent);
                            return null;
                        }
                    });
        });


    }

    private void updateUI() {
        if (!loginContext.isLoggedIn()) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Successfully logout", Toast.LENGTH_SHORT).show();
        }
    }


    private void setProfilePic(Uri imageUri, ImageView imageView) {
        Glide.with(getApplicationContext()).load(imageUri).apply(RequestOptions.circleCropTransform()).into(imageView);
    }





}