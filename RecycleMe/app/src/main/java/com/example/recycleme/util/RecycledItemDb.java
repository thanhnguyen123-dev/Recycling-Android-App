package com.example.recycleme.util;

import android.content.Context;

import com.example.recycleme.util.tree.AVLTreeItem;
import com.example.recycleme.model.RecycledItem;
import com.example.recycleme.dao.FirebaseRecycledItemDAO;
import com.example.recycleme.dao.RecycledItemDAO;
import com.example.recycleme.interfaces.Observer;
import com.example.recycleme.interfaces.Subject;
import com.example.recycleme.search.SearchQueryParser;
import com.example.recycleme.search.Tokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a class that reads the data from the DAO.
 * After the data is read, then the data is put into the AVLTreeItem.
 * This class also stream the extra data to the current app.
 *
 * @author Julius Liem
 * @author Devansu Yadav
 */
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
        this.recycledItemStream = new FirebaseRecycledItemDAO("mock_data_forstream.json", context, Local);
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

    /**
     * @author Devansu Yadav
     */
    public List<RecycledItem> search(String searchQuery) throws SearchQueryParser.InvalidQueryException {
        Tokenizer tokenizer = new Tokenizer(searchQuery);
        SearchQueryParser parser = new SearchQueryParser(tokenizer);
        return parser.parseSearchQuery().evaluateSearchExp(this.recycledItemAVLTree);
    }


    private void addRecycledItemToStream(RecycledItem recycledItem) {
        this.recycledItemAVLTree.insert(recycledItem);
        this.notifyAllObservers(recycledItem.getBrandName() + " " + recycledItem.getItem() + " has been added to the stream!");
    }

    /**
     * @author Devansu Yadav
     */
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

    /**
     * @author Devansu Yadav
     */
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

    /**
     * Method that informs the observers as part of the Observer pattern
     * @author Julius Liem
     */
    @Override
    public void notifyAllObservers(String message) {
        for (Observer obs: this.observers) {
            obs.update(message);
        }
    }
}
