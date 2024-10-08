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
import androidx.annotation.NonNull;

import com.example.recycleme.login.LoginContext;
import com.example.recycleme.util.LogUtil;
import com.example.recycleme.util.UserProfileUtil;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


/**
 * An activity to view the user profile with buttons to select and upload user profile image.
 * I use Image Picker - a library to select an image from phone using camera or gallery
 * Source: <a href="https://github.com/Dhaval2404/ImagePicker">Image Picker</a>
 * @author Le Thanh Nguyen - u7594144
 */
public class ProfileActivity extends BaseActivity {
    private LoginContext loginContext;
    private TextView usernameTextView;
    private Button logOutButton;
    private Button uploadButton;
    private Button selectButton;
    private ActivityResultLauncher<Intent> imageLauncher;
    private Uri selectedImageUri;
    private ImageView profilePicImageView;
    private StorageReference storageReference;
    private FirebaseUser firebaseUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_profile, contentFrameLayout);

        initView();
        getUserImage();
        loginContext = LoginContext.getInstance();
        String emailText = loginContext.getUserEmail();
        String username = LogUtil.getUsernameFromEmail(emailText);
        usernameTextView.setText(username);

        logOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginContext.logout();
                updateUI();
            }
        });

        setImageLauncher();

        selectButton.setOnClickListener(v -> {
            ImagePicker.with(this).cropSquare().compress(512).maxResultSize(512, 512)
                    .createIntent(new Function1<Intent, Unit>() {
                        @Override
                        public Unit invoke(Intent intent) {
                            imageLauncher.launch(intent);
                            return null;
                        }
                    });
        });

        uploadButton.setOnClickListener(v -> {
            if (selectedImageUri != null) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                UserProfileUtil.getProfilePicStorageReference(firebaseUser.getUid());
                storageReference.putFile(selectedImageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        Toast.makeText(getApplicationContext(), "Updated profile image", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void updateUI() {
        if (!loginContext.isLoggedIn()) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Successfully logout", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        usernameTextView = findViewById(R.id.otherProfileUsername);
        profilePicImageView = findViewById(R.id.otherUserImageView);
        logOutButton = findViewById(R.id.logout_button);
        selectButton = findViewById(R.id.selectButton);
        uploadButton = findViewById(R.id.uploadButton);
    }

    private void getUserImage() {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference()
                .child("profile_image")
                .child(firebaseUser.getUid());
        UserProfileUtil.retrieveUserImage(firebaseUser.getUid(), getApplicationContext(), profilePicImageView);

    }

    private void setImageLauncher() {
        imageLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                res -> {
                    if (res.getResultCode() == Activity.RESULT_OK) {
                        Intent data = res.getData();
                        if (data != null && data.getData() != null) {
                            selectedImageUri = data.getData();
                            UserProfileUtil.setProfilePic(getApplicationContext(), selectedImageUri, profilePicImageView);

                        }
                    }
                }
        );
    }
}