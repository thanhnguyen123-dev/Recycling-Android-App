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

/**
 * Base activity class that provides a navigation drawer menu. The menu contents and behavior are the same across
 * multiple activities.
 * <p>
 * This code is based on an answer from Stack Overflow with modifications:
 * https://stackoverflow.com/questions/19451715/same-navigation-drawer-in-different-activities
 * <p>
 * The original code is licensed under CC BY-SA 3.0:
 * https://creativecommons.org/licenses/by-sa/3.0/
 * <p>
 * Modifications include:
 * - Added login functionality to the navigation drawer
 * - Integrated with a custom LoginContext class to handle login state
 * - Updated the navigation item click handling to start specific activities based on the selected menu item
 * - Removed the onPostCreate method as it is not needed in this implementation
 * Written by: Julius Liem - u7724204
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
                // Handle navigation item clicks
                if (menuItem.getItemId() == R.id.home) {
                    Intent intent = new Intent(BaseActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (menuItem.getItemId() == R.id.login) {
                    if (LoginContext.getInstance().isLoggedIn()) {
                        Intent intent = new Intent(BaseActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }
                // Close the navigation drawer
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

}
