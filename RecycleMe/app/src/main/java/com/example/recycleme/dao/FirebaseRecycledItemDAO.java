package com.example.recycleme.dao;

import com.example.recycleme.RecycledItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseRecycledItemDAO implements RecycledItemDAO {

    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("recycledItems");




    @Override
    public void addRecycledItem(RecycledItem recycledItem) {
        String id = mDatabase.push().getKey();
        recycledItem.setId(id);
        mDatabase.child(id).setValue(recycledItem);
    }

    @Override
    public void updateRecycledItem(RecycledItem recycledItem) {
        mDatabase.child(recycledItem.getId()).setValue(recycledItem);
    }

    @Override
    public void deleteRecycledItem(int id) {
        mDatabase.child(String.valueOf(id)).removeValue();
    }


    //need to finish
    @Override
    public RecycledItem getRecycledItemByID(int id) {
        return null;
    }

    @Override
    public List<RecycledItem> getAllRecycledItems() {
        List<RecycledItem> recycledItemList = new ArrayList<>();
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                recycledItemList.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    RecycledItem recycledItem = postSnapshot.getValue(RecycledItem.class);
                    recycledItemList.add(recycledItem);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Oopsie
            }
        });
        return recycledItemList;
    }

}

