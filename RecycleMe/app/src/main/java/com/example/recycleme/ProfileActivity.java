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

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recycleme.login.LoginContext;
import com.example.recycleme.util.LogUtil;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


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
    private FirebaseStorage firebaseStorage;


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
        firebaseStorage = FirebaseStorage.getInstance();
        retrieveUserImage();

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
                            selectedImageUri = data.getData();
                            setProfilePic(selectedImageUri, profilePicImageView);

                        }
                    }
                }
                );

        selectButton = findViewById(R.id.selectButton);
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

        uploadButton = findViewById(R.id.uploadButton);
        uploadButton.setOnClickListener(v -> {
            if (selectedImageUri != null) {
                getProfilePicStorageReference();
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

    private void retrieveUserImage() {
        getProfilePicStorageReference();
        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri uri = task.getResult();
                    setProfilePic(uri, profilePicImageView);
                }
            }
        });
    }

    private void getProfilePicStorageReference() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference()
                .child("profile_image")
                .child(firebaseUser.getUid());
    }

    private void setProfilePic(Uri imageUri, ImageView imageView) {
        Glide.with(getApplicationContext()).load(imageUri).apply(RequestOptions.circleCropTransform()).into(imageView);
    }





}