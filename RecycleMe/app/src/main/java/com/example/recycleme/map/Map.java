package com.example.recycleme.map;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.Random;

public class Map {
    public static final int FINE_PERMISSION_CODE = 1;
    Location currentLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private GoogleMap map;
    private Context context;

    public Map(Context context, GoogleMap map) {
        this.context = context;
        this.map = map;
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        getLastLocation();
    }

    public void getLastLocation() {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_PERMISSION_CODE);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null){
                    currentLocation = location;
                    onMapReady(map);
                }
            }
        });
    }

    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        // Check if we have the necessary permissions to show user location
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Enable showing user location
            map.setMyLocationEnabled(true);
        }

        LatLng test = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(test, 15));
        map.getUiSettings().setZoomControlsEnabled(true);

        Random random = new Random();
        int numMarkers = random.nextInt(6) + 10;
        for (int i = 0; i < numMarkers; i++) {
            double distanceInKm = 1 + random.nextDouble() * 49;
            double angle = random.nextDouble() * 360;

            double dLat = Math.cos(Math.toRadians(angle)) * distanceInKm / 111;
            double dLng = Math.sin(Math.toRadians(angle)) * distanceInKm / 111;

            LatLng markerLatLng = new LatLng(test.latitude + dLat, test.longitude + dLng);
            map.addMarker(new MarkerOptions().position(markerLatLng).title("Recycling Plant"));
        }
    }
}
