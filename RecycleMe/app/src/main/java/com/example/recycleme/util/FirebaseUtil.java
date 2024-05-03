package com.example.recycleme.util;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseUtil {
    private static FirebaseFirestore firebaseFirestore;
    private static String userUid;
    private static FirebaseAuth mAuth;
    public static DocumentReference getUserInfo() {
        firebaseFirestore = FirebaseFirestore.getInstance();
        userUid = getUserUid();
        return firebaseFirestore.collection("users").document(userUid);
    }
    public static String getUserUid() {
        mAuth = FirebaseAuth.getInstance();
        return mAuth.getUid();
    }
}
