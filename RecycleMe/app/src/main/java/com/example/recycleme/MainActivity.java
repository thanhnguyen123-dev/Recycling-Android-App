package com.example.recycleme;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.recycleme.dao.RecycledItemDAO;
import com.example.recycleme.dao.RecycledItemDAOJsonImp;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements Observer {

    private RecyclerView recyclerView;
    private RecycledViewAdapter adapter;
    private RecycledItemDb recycledItemDb;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        this.recycledItemDb = RecycledItemDb.getInstance(getApplicationContext());
        this.recycledItemDb.attach(this);
        adapter = new RecycledViewAdapter(this.recycledItemDb.getCurrentData());

        this.recyclerView = findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adapter);

        this.swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        this.swipeRefreshLayout.setOnRefreshListener(() -> {
            adapter.setRecycledItems(recycledItemDb.getCurrentData());
            adapter.notifyDataSetChanged();

            swipeRefreshLayout.setRefreshing(false);
        });

        recycledItemDb.startStream();
    }

    protected void onDestroy() {
        super.onDestroy();

        recycledItemDb.stopStream();
    }


    @Override
    public void update(String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                View view = findViewById(R.id.content_frame);
                Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
                snackbar.setAction("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }
        });
    }
}
