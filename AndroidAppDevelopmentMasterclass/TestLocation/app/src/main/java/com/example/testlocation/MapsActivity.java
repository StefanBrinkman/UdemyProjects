package com.example.testlocation;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/*
    TO DO => API 28 emulator + Google Services enabled
    Get current location from the user.
    Add error handling for when maps wasn't able to load and when permisson wasn't given.
    Location permission denied, ask again.
    Ask user if app may use location. (fine location, coarse location)
    Get location of user on startup if permission is granted.

    https://romannurik.github.io/AndroidAssetStudio/ => Icon launcher => You can generate custom launch icon for the app.
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng sydney = new LatLng(
                -34,
                151
        );

        mMap.addMarker(
                new MarkerOptions()
                        .position(sydney)
                        .title("Marker in Sydney")
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
