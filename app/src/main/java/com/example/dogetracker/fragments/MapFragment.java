package com.example.dogetracker.fragments;


import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.dogetracker.R;
import com.example.dogetracker.location.LocationChanged;
import com.example.dogetracker.location.SystemLocationManager;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapFragment extends Fragment implements OnMapReadyCallback, LocationChanged {

    private MapView mapView;
    private GoogleMap googleMap;
    private static final String FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION;
    public static final int LOCATION_PERMISSION_REQUEST_CODE = 237;
    private SystemLocationManager systemLocationManager;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    /**
     * Check location permissions
     * if "yes" subscribe to location changes via SystemLocationManager
     * checking locationPermissions before every attempt of subscribing to location permissions
     */

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.app_name);

        //   MapView mapView = view.findViewById(R.id.map_view);
        FloatingActionButton buttonCurrentLocation = view.findViewById(R.id.button_current_location);
        mapView = view.findViewById(R.id.map_view);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);//асинхронная задача
        buttonCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getLocationPermission()){
                    systemLocationManager.getLastLocation();
                }
            }
        });


    }

    private boolean getLocationPermission() {
        String[] permissions = {FINE_LOCATION, COARSE_LOCATION};
        Context context = getActivity();
        if (ContextCompat.checkSelfPermission(context, FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            initLocationManager();
             return true;
        } else {
            requestPermissions(permissions, LOCATION_PERMISSION_REQUEST_CODE);
            return false;
        }

    }


    /*
     * [0] = 1
     * [1] = 4
     * [2] = 4
     * [3] = 7
     * [4] = 0
     *
     * length == 5
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        switch (requestCode) {
            case LOCATION_PERMISSION_REQUEST_CODE: {
                boolean isPermissionsGranted = true;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        isPermissionsGranted = false;
                        break;
                    }
                }
                if (isPermissionsGranted) {
                    initLocationManager();
                }
            }
        }
    }

    private void initLocationManager(){
        if (systemLocationManager == null) {
            systemLocationManager = new SystemLocationManager(getActivity(), this);
        }
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    public void onNewLocation(Location location) {

    }
}