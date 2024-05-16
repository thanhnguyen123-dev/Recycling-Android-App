package com.example.recycleme;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.example.recycleme.map.gpsMap;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * This activity displays a GoogleMap in the "Map" tab, with the user's location and nearby recycling plants.
 * Currently, it is using mock data for the recycling plants, but will return proper location information on real devices.
 * This class uses the google maps API.
 * @author Harrison Black
 */
public class MapActivity extends BaseActivity implements OnMapReadyCallback {
    private gpsMap gpsMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_map, contentFrameLayout);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gpsMap = new gpsMap(this, googleMap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == gpsMap.FINE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                gpsMap.getLastLocation();
            } else {
                Toast.makeText(this, "Location permission is denied!\n(つ﹏<。)", Toast.LENGTH_LONG).show();
            }
        }
    }
}


