package com.example.recycleme;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

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

import com.example.recycleme.dao.RecycledItemDAO;
import com.example.recycleme.dao.RecycledItemDAOJsonImp;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends BaseActivity {

    private RecyclerView recyclerView;
    private RecycledViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        RecycledItemDAO recycledItemDAO = new RecycledItemDAOJsonImp("mock_data_updated.json", getApplicationContext());

        this.recyclerView = findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<RecycledItem> recycledItems = recycledItemDAO.getAllRecycledItems();

        adapter = new RecycledViewAdapter(recycledItems);
        recyclerView.setAdapter(adapter);
    }
}
