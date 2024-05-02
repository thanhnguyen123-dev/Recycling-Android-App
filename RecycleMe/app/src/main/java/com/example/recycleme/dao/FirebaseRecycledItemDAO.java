package com.example.recycleme.dao;

import android.content.Context;
import android.content.res.AssetManager;
import com.example.recycleme.RecycledItem;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class FirebaseRecycledItemDAO implements RecycledItemDAO {

    private final String FILE_NAME;
    private Gson gson;
    private List<RecycledItem> allRecycledItems;
    private Context context;
    private int f;

    FirebaseStorage storage = FirebaseStorage.getInstance();

    public FirebaseRecycledItemDAO(String fileName, Context context, int f) {
        FILE_NAME = fileName;
        this.gson = new Gson();
        this.context = context;
        this.f = f;
        this.allRecycledItems = this.getAllRecycledItemsHelper();
    }


    @Override
    public void addRecycledItem(RecycledItem recycledItem) {
        List<RecycledItem> recycledItems = getAllRecycledItems();
        recycledItems.add(recycledItem);
        saveRecycledItems(recycledItems);
    }

    @Override
    public void updateRecycledItem(RecycledItem recycledItem) {
        List<RecycledItem> recycledItems = getAllRecycledItems();

        for (int i = 0; i < recycledItems.size(); i++) {
            if (recycledItems.get(i).getId() == recycledItem.getId()) {
                recycledItems.set(i, recycledItem);
            }
        }
    }

    @Override
    public void deleteRecycledItem(int id) {
        List<RecycledItem> recycledItems = getAllRecycledItems();
        recycledItems.removeIf(item -> Integer.parseInt(item.getId()) == id);
    }

    @Override
    public RecycledItem getRecycledItemByID(int id) {
        return null;
    }

    private List<RecycledItem> getAllRecycledItemsHelper() {
        AtomicReference<List<RecycledItem>> recycledItems = new AtomicReference<>(new ArrayList<>());
        try {
            Type type = new TypeToken<List<RecycledItem>>() {}.getType();
            if (f == 0) {
                AssetManager assetManager = this.context.getAssets();
                InputStream inputStream = assetManager.open(this.FILE_NAME);
                InputStreamReader reader = new InputStreamReader(inputStream);
                recycledItems.set(gson.fromJson(reader, type));
            } else if (f == 1) {
                StorageReference firebaseFile = storage.getReferenceFromUrl("gs://recyclingapp-login-firebase.appspot.com/" + FILE_NAME);
                firebaseFile.getBytes(15000000).addOnSuccessListener(bytes -> {
                    String jsonString = new String(bytes, StandardCharsets.UTF_8);
                    List<RecycledItem> itemsFromFirebase = gson.fromJson(jsonString, type);
                    recycledItems.set(itemsFromFirebase);
                    System.out.println("Proof that this is working "+recycledItems);
                }).addOnFailureListener(exception -> {
                    // Handle failure
                    System.out.println("Failed to retrieve data from Firebase storage: " + exception.getMessage());
                });
            }
        } catch (IOException e) {
            System.out.println("IO Exception");
        }
        return recycledItems.get();
    }

    @Override
    public List<RecycledItem> getAllRecycledItems() {
        return this.allRecycledItems;
    }

    private void saveRecycledItems(List<RecycledItem> recycledItems) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(recycledItems, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

