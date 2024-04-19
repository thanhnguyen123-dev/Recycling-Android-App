package com.example.recycleme;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import com.example.recycleme.dao.RecycledItemDAO;
import com.example.recycleme.dao.RecycledItemDAOJsonImp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecycledItemDb implements Subject{
    private static RecycledItemDb instance;
    private RecycledItemDAO recycledItemDAO;
    private RecycledItemDAO recycledItemStream;
    private ArrayList<Observer> observers;
    private ArrayList<RecycledItem> currentData;

    private RecycledItemDb(Context context){
        this.recycledItemDAO = new RecycledItemDAOJsonImp("mock_data_updated.json", context);
        this.recycledItemStream = new RecycledItemDAOJsonImp("mock_data_forstream.json", context);
        this.currentData = new ArrayList<>(recycledItemDAO.getAllRecycledItems());
        this.observers = new ArrayList<Observer>();
    }


    public static RecycledItemDb getInstance(Context context) {
        if (instance == null) {
            instance = new RecycledItemDb(context);
        }

        return instance;
    }

    public ArrayList<RecycledItem> getCurrentData() {
        return this.currentData;
    }


    public void addRecycledItemPersistent(RecycledItem recycledItem) {
        recycledItemDAO.addRecycledItem(recycledItem);
    }

    public void updateRecycledItem(RecycledItem recycledItem) {
        recycledItemDAO.updateRecycledItem(recycledItem);
    }

    public void deleteRecycledItem(int id) {
        recycledItemDAO.deleteRecycledItem(id);
    }

    private void addRecycledItemToStream(RecycledItem recycledItem) {
        this.currentData.add(0, recycledItem);
        this.notifyAllObservers(recycledItem.getBrandName() + " " + recycledItem.getItem() + " has been added to the stream!");
    }

    public void refreshStreamRandomly() {
        List<RecycledItem> streamItems = recycledItemStream.getAllRecycledItems();
        if (!streamItems.isEmpty()) {
            int randomIndex = new Random().nextInt(streamItems.size());
            RecycledItem randomItem = streamItems.get(randomIndex);

            addRecycledItemToStream(randomItem);
        }
    }
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyAllObservers(String message) {
        for (Observer obs: this.observers) {
            obs.update(message);
        }
    }
}
