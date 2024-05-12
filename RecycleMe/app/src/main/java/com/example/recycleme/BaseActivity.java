package com.example.recycleme;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
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
 * https://stackoverflow.com/questions/36095691/android-navigationdrawer-multiple-activities-same-menu
 * <p>
 * The original code is licensed under CC BY-SA 3.0:
 * https://creativecommons.org/licenses/by-sa/3.0/
 * <p>
 *
 * @author Julius Liem - u7724204
 * @author Devansu Yadav
 */

public class BaseActivity extends AppCompatActivity {
    protected DrawerLayout drawerLayout;
    protected NavigationView navView;
    protected Toolbar toolbar;
    protected ActionBarDrawerToggle actionBarDrawerToggle;


    /**
     * @author Devansu Yadav
     */
    private enum MenuItemId {
        HOME(R.id.home),
        REGISTER_BUTTON(R.id.register_button),
        CHATS(R.id.chats),
        CART(R.id.cart),
        RECORD(R.id.record),
        STATISTIC(R.id.statistic),
        MAP(R.id.map),
        UNKNOWN(-1);

        private final int id;

        MenuItemId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public static MenuItemId fromId(int id) {
            for (MenuItemId menuItemId : values()) {
                if (menuItemId.getId() == id) {
                    return menuItemId;
                }
            }
            return UNKNOWN;
        }
    }

    /**
     * @author Julius Liem
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_base);
        initViews();
    }

    /**
     * @author Julius Liem
     */
    protected void initViews() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navView = (NavigationView) findViewById(R.id.navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupDrawerToggle();
        setupNavListener();
    }

    /**
     * @author Julius Liem
     */
    private void setupDrawerToggle() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * @author Devansu Yadav
     */
    private void setupNavListener() {
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // Handle navigation item clicks
                Intent intent;
                switch (MenuItemId.fromId(menuItem.getItemId())) {
                    case HOME:
                        intent = new Intent(BaseActivity.this, MainActivity.class);
                        break;
                    case REGISTER_BUTTON:
                        if (LoginContext.getInstance().isLoggedIn()) {
                            intent = new Intent(BaseActivity.this, ProfileActivity.class);
                        } else {
                            intent = new Intent(BaseActivity.this, LoginActivity.class);
                        }
                        break;
                    case CHATS:
                        intent = new Intent(BaseActivity.this, ChatsMainActivity.class);
                        break;
                    case CART:
                        intent = new Intent(BaseActivity.this, CartActivity.class);
                        break;
                    case RECORD:
                        intent = new Intent(BaseActivity.this, RecordActivity.class);
                        break;
                    case STATISTIC:
                        intent = new Intent(BaseActivity.this, StatisticActivity.class);
                        break;
                    case MAP:
                        intent = new Intent(BaseActivity.this, MapActivity.class);
                        break;
                    default:
                        return false;
                }
                startActivity(intent);
                // Close the navigation drawer
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

}
