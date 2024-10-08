package com.example.recycleme.dao;

import com.example.recycleme.model.RecycledItem;


import java.util.List;

/**
 * Interface for reading JSON files using DAO pattern
 * @author Julius Liem
 */
public interface RecycledItemDAO {
    void addRecycledItem(RecycledItem recycledItem);
    void updateRecycledItem(RecycledItem recycledItem);
    void deleteRecycledItem(int id);
    RecycledItem getRecycledItemByID(int id);
    List<RecycledItem> getAllRecycledItems();
}
