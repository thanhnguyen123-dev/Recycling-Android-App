package com.example.recycleme.dao;

import android.content.Context;
import android.content.res.AssetManager;

import com.example.recycleme.RecycledItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecycledItemDAOJsonImp implements RecycledItemDAO{

    private final String FILE_NAME;
    private Gson gson;
    private List<RecycledItem> allRecycledItems;
    private Context context;

    public RecycledItemDAOJsonImp(String fileName, Context context) {
        FILE_NAME = fileName;
        this.gson = new Gson();
        this.context = context;
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
        recycledItems.removeIf(item -> item.getId() == id);
    }

    @Override
    public RecycledItem getRecycledItemByID(int id) {
        return null;
    }

    private List<RecycledItem> getAllRecycledItemsHelper() {
        List<RecycledItem> recycledItems = new ArrayList<>();
        try {
            AssetManager assetManager = this.context.getAssets();
            InputStream inputStream = assetManager.open(this.FILE_NAME);
            InputStreamReader reader = new InputStreamReader(inputStream);
            Type type = new TypeToken<List<RecycledItem>>() {}.getType();
            recycledItems = gson.fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("IO Exception");
        }

        return recycledItems;
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
