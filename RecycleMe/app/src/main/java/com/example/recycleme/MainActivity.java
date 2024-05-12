package com.example.recycleme;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import androidx.appcompat.widget.SearchView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.recycleme.adapter.RecycledViewAdapter;
import com.example.recycleme.cart.Cart;
import com.example.recycleme.interfaces.Observer;
import com.example.recycleme.model.RecycledItem;
import com.example.recycleme.search.SearchQueryParser;
import com.example.recycleme.util.RecycledItemDb;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

/**
 * This is a class for Main Activity. The class shows a list of recycled items that can be added
 * to the cart.
 *
 * @author Devansu Yadav (initSwipeRefresh() and startStream())
 * @author Julius Liem
 */
public class MainActivity extends BaseActivity implements Observer {

    private RecyclerView recyclerView;
    private RecycledViewAdapter adapter;
    private RecycledItemDb recycledItemDb;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SearchView searchView;
    private ImageView noResultsImage;
    private TextView noResultsText;

    Cart cart = Cart.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_main, contentFrameLayout);

        initElements();
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

    private void initElements() {
        this.searchView = findViewById(R.id.search_view);
        this.noResultsImage = findViewById(R.id.no_results_image);
        this.noResultsText = findViewById(R.id.no_results_text);

        this.recycledItemDb = RecycledItemDb.getInstance(getApplicationContext());
        this.recycledItemDb.attach(this);
        adapter = new RecycledViewAdapter(this.recycledItemDb.getCurrentData());

        this.recyclerView = findViewById(R.id.recyclerView);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<RecycledItem> recycledItems = recycledItemDb.getCurrentData();

        adapter = new RecycledViewAdapter(recycledItems);
        recyclerView.setAdapter(adapter);

        initSearchView();
        initSwipeRefresh();

        recycledItemDb.startStream();
    }


    private void initSearchView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    List<RecycledItem> searchResults = recycledItemDb.search(query);
                    if (searchResults.isEmpty()) {
                        noResultsImage.setVisibility(View.VISIBLE);
                        noResultsText.setVisibility(View.VISIBLE);
                    } else {
                        noResultsImage.setVisibility(View.GONE);
                        noResultsText.setVisibility(View.GONE);
                    }
                    adapter.setRecycledItems(searchResults);
                    adapter.notifyDataSetChanged();
                } catch(SearchQueryParser.InvalidQueryException e) {
                    Snackbar.make(searchView, e.getMessage(), Snackbar.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.isEmpty()) {
                    noResultsImage.setVisibility(View.GONE);
                    noResultsText.setVisibility(View.GONE);
                    adapter.setRecycledItems(recycledItemDb.getCurrentData());
                    adapter.notifyDataSetChanged();
                }
                return false;
            }
        });
    }

    private void initSwipeRefresh() {
        this.swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        this.swipeRefreshLayout.setOnRefreshListener(() -> {
            Log.d("ITEMNUM", String.valueOf(recycledItemDb.getCurrentData().size()));
            searchView.setQuery("", false);
            swipeRefreshLayout.setRefreshing(false);
        });
    }
}
