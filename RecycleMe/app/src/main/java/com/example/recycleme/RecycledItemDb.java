package com.example.recycleme;

import android.content.Context;

import com.example.recycleme.cart.AVLTree;
import com.example.recycleme.cart.AVLTreeItem;
import com.example.recycleme.dao.FirebaseRecycledItemDAO;
import com.example.recycleme.dao.RecycledItemDAO;
import com.example.recycleme.dao.RecycledItemDAOJsonImp;
import com.example.recycleme.interfaces.Observer;
import com.example.recycleme.interfaces.Subject;

import java.util.ArrayList;
import java.util.List;

public class RecycledItemDb implements Subject {
    private static RecycledItemDb instance;
    private RecycledItemDAO recycledItemDAO;
    private RecycledItemDAO recycledItemStream;
    private ArrayList<Observer> observers;
    private ArrayList<RecycledItem> currentData;
    private AVLTreeItem recycledItemAVLTree;
    private Thread streamThread;

    private int Local = 0;
    private int Online = 1;
    private volatile boolean isStreamRunning;


    private RecycledItemDb(Context context){
        this.recycledItemDAO = new FirebaseRecycledItemDAO("mock_data_updated.json", context, Online); //This now pulls the json file from firebase!!!
        this.recycledItemStream = new RecycledItemDAOJsonImp("mock_data_forstream.json", context);
        this.currentData = new ArrayList<>(recycledItemDAO.getAllRecycledItems());
        this.recycledItemAVLTree = new AVLTreeItem();
        this.observers = new ArrayList<Observer>();

        for (RecycledItem item: currentData) {
            this.recycledItemAVLTree.insert(item);
        }
    }


    public static RecycledItemDb getInstance(Context context) {
        if (instance == null) {
            instance = new RecycledItemDb(context);
        }

        return instance;
    }

    public ArrayList<RecycledItem> getCurrentData() {
        return (ArrayList<RecycledItem>) this.recycledItemAVLTree.traverse();
    }


    public void addRecycledItemPersistent(RecycledItem recycledItem) {
        recycledItemDAO.addRecycledItem(recycledItem);
    }

    public void updateRecycledItem(RecycledItem recycledItem) {
        recycledItemDAO.updateRecycledItem(recycledItem);
    }

    public List<RecycledItem> search(String name, String brand, String material) {
        return this.recycledItemAVLTree.findItems(name, brand, material);
    }


    private void addRecycledItemToStream(RecycledItem recycledItem) {
        this.recycledItemAVLTree.insert(recycledItem);
        this.notifyAllObservers(recycledItem.getBrandName() + " " + recycledItem.getItem() + " has been added to the stream!");
    }

    public void startStream() {
        if (streamThread == null ||  !streamThread.isAlive()) {
            isStreamRunning = true;
            streamThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    List<RecycledItem> streamList = recycledItemStream.getAllRecycledItems();

                    for (RecycledItem item : streamList) {
                        addRecycledItemToStream(item);
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            streamThread.start();
        }
    }

    public void stopStream() {
        isStreamRunning = false;
        if (streamThread != null && streamThread.isAlive()) {
            streamThread.interrupt();
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
