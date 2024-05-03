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

/**
 * An implementation of the RecycledItemDAO interface that uses JSON files for data persistence.
 * <p>
 * This class provides methods to perform operations on RecycledItem objects.
 * The data is stored in a JSON file specified by the FILE_NAME parameter.
 * <p>
 * In the getAllRecycledItemsHelper() method, generative AI assistance was utilized to convert the JSON file
 * into a list of RecycledItem objects. The AI was asked, "How do I turn a JSON file into a list of items?"
 * The AI provided guidance on using the Gson library to parse the JSON file and convert it into a list of objects.
 * <p>
 * The class uses the Gson library. It also utilizes the Android AssetManager
 * to read the JSON file from the assets directory.
 *
 * @author Julius Liem
 */
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
        recycledItems.removeIf(item -> Integer.parseInt(item.getId()) == id);
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
