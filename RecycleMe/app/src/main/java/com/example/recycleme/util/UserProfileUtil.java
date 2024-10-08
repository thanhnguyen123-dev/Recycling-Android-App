package com.example.recycleme.util;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.recycleme.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

/**
 * An utility class to handle communication between the app and Firebase Storage
 * to retrieve user profile image.
 * I use Glide - an open source frame work for media management
 * Source: <a href="https://bumptech.github.io/glide/">Glide</a>
 * @author Le Thanh Nguyen - u7594144
 * */
public class UserProfileUtil {
    private static StorageReference storageReference;

    public static void retrieveUserImage(String id, Context context, ImageView imageView) {
        getProfilePicStorageReference(id);
        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri uri = task.getResult();
                    setProfilePic(context, uri, imageView);
                }
            }
        });
    }

    public static void getProfilePicStorageReference(String id) {
        storageReference = FirebaseStorage.getInstance().getReference()
                .child("profile_image")
                .child(id);
    }

    public static void setProfilePic(Context context, Uri imageUri, ImageView imageView) {
        Glide.with(context).load(imageUri).apply(RequestOptions.circleCropTransform()).into(imageView);
    }
}
