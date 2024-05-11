package com.example.recycleme;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.core.view.GravityCompat;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.recycleme.login.LoginContext;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;
import java.util.Map;

/**
 * Base activity class that provides a navigation drawer menu. The menu contents and behavior are the same across
 * multiple activities.
 * <p>
 * This code is based on an answer from Stack Overflow with modifications:
 * https://stackoverflow.com/questions/36095691/android-navigationdrawer-multiple-activities-same-menu
 * <p>
 * The original code is licensed under CC BY-SA 3.0:
 * https://creativecommons.org/licenses/by-sa/3.0/
 * <p>
 *
 * @author Julius Liem - u7724204
 */

public class BaseActivity extends AppCompatActivity {
    protected DrawerLayout drawerLayout;
    protected NavigationView navView;
    protected Toolbar toolbar;
    protected ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);
        initViews();
    }

    protected void initViews() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupDrawerToggle();
        setupNavListener();
    }


    private void setupDrawerToggle() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void setupNavListener() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Map<Integer, Class<?>> activityMap = new HashMap<>();
                activityMap.put(R.id.home, MainActivity.class);
                activityMap.put(R.id.login, LoginContext.getInstance().isLoggedIn() ? ProfileActivity.class : LoginActivity.class);
                activityMap.put(R.id.cart, CartActivity.class);
                activityMap.put(R.id.record, RecordActivity.class);
                activityMap.put(R.id.statistic, StatisticActivity.class);
                activityMap.put(R.id.map, MapActivity.class);

                Class<?> activityClass = activityMap.get(menuItem.getItemId());

                if (activityClass != null) {
                    Intent intent = new Intent(BaseActivity.this, activityClass);
                    startActivity(intent);
                }

                // Close the navigation drawer
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

}
