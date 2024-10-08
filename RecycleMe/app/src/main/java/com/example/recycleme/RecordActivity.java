package com.example.recycleme;

import android.os.Bundle;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.recycleme.cart.NodeData;
import com.example.recycleme.cart.UserTree;
import com.example.recycleme.adapter.RecordAdapter;
import com.example.recycleme.model.RecycledItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This is an activity that shows the user's recycling history.
 * @author Julius Liem
 */
public class RecordActivity extends BaseActivity {

    private List<NodeData<List<RecycledItem>>> nodeDataList;
    private RecyclerView recyclerView;
    private ConcatAdapter concatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_record, contentFrameLayout);

        this.nodeDataList = UserTree.getInstance().traverseReturnItemAndDate();

        recyclerView = findViewById(R.id.record_history_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateAdapter();
    }

    private void updateAdapter() {
        List<RecyclerView.Adapter<RecyclerView.ViewHolder>> adapters = new ArrayList<>();

        // Create a new adapter for each NodeData
        // This way there are many adapters, each w/ date as the header
        for (NodeData<List<RecycledItem>> nodedata: nodeDataList) {
            LocalDateTime date = nodedata.getDateTime();
            List<RecycledItem> items = nodedata.getValue();

            RecordAdapter recordAdapter = new RecordAdapter(date, items, new RecordAdapter.OnDeleteClickListener() {
                @Override
                public void onDeleteClick(LocalDateTime date) {
                    UserTree.getInstance().deleteItem(date);
                    nodeDataList = UserTree.getInstance().traverseReturnItemAndDate();
                    updateAdapter();
                }
            });
            adapters.add(recordAdapter);
        }

        concatAdapter = new ConcatAdapter(adapters);
        recyclerView = findViewById(R.id.record_history_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(concatAdapter);
    }
}