package com.example.recycleme.dao;

import android.content.Context;
import android.content.res.AssetManager;
import com.example.recycleme.model.RecycledItem;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *  An implementation of the RecycledItemDAO interface.
 *  This class is used to grab recyclable items from either a firebase or local json file.
 * @author Harrison Black
 * @author Julius Liem - u7724204 (saveRecycledItem() method and read from offline data)
 */

public class FirebaseRecycledItemDAO implements RecycledItemDAO {

    private final String FILE_NAME;
    private Gson gson;
    private Future <List<RecycledItem>> allRecycledItemsFuture;

    private Future<List<RecycledItem>> future;

    private List<RecycledItem> allItems;
    private Context context;
    private int f; //0 if you want to use a local file, 1 if you want to use a FirebaseFile

    FirebaseStorage storage = FirebaseStorage.getInstance();

    public FirebaseRecycledItemDAO(String fileName, Context context, int f) {
        FILE_NAME = fileName;
        this.gson = new Gson();
        this.context = context;
        this.f = f;
        this.allRecycledItemsFuture = this.getAllRecycledItemsHelper();
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
        recycledItems.removeIf(item -> item.getId() == id);
    }

    @Override
    public RecycledItem getRecycledItemByID(int id) {
        return null;
    }

    //Harrison Black
    private Future<List<RecycledItem>> getAllRecycledItemsHelper() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        return executor.submit(() -> {
            List<RecycledItem> recycledItems = new ArrayList<>();
            try {
                Type type = new TypeToken<List<RecycledItem>>() {}.getType();

                if (f == 0) { //Julius Liem
                    AssetManager assetManager = this.context.getAssets();
                    InputStream inputStream = assetManager.open(this.FILE_NAME);
                    InputStreamReader reader = new InputStreamReader(inputStream);
                    recycledItems.addAll(gson.fromJson(reader, type));
                } else if (f == 1) { //Harrison Black
                    StorageReference firebaseFile = storage.getReferenceFromUrl("gs://recyclingapp-login-firebase.appspot.com/" + FILE_NAME);
                    Task<byte[]> task = firebaseFile.getBytes(15000000);
                    Tasks.await(task);
                    if (task.isSuccessful()) {
                        String jsonString = new String(task.getResult(), StandardCharsets.UTF_8);
                        List<RecycledItem> itemsFromFirebase = gson.fromJson(jsonString, type);
                        recycledItems.addAll(itemsFromFirebase);
                    }
                }
            } catch (IOException | InterruptedException | ExecutionException e) {
                e.printStackTrace(); //Hopefully you never see this in the console.
            }

            return recycledItems; //Only returns this when the firebase file is done.
        });
    }

    public void updateRecycledItems() {
        future = getAllRecycledItemsHelper();
        // Use a separate thread to wait for the result and update allRecycledItems
        new Thread(() -> {
            try {
                allItems = future.get(); // This will block until the result is ready
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
    }

    //Harrison Black
    public List<RecycledItem> getAllRecycledItems() {
        updateRecycledItems();
        while (future == null || !future.isDone()) { //Runs while the json file hasn't finished downloading
            try {
                Thread.sleep(100); // Wait for 100ms then try again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.allItems;
    }

    //Julius Liem
    private void saveRecycledItems(List<RecycledItem> recycledItems) {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            gson.toJson(recycledItems, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}





