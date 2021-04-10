package com.example.dogetracker.location;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;


import com.example.dogetracker.fragments.MapFragment;

import java.util.List;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SystemLocationManager implements LocationListener {

    private LocationManager locationManager;
    private static final long MIN_UPDATE_INTERVAL_MS = 1000;
    private static final float MIN_DISTANCE_CHANGE_UPDATES_M = 0;
    private LocationChanged locationChanged;
    private Location lastLocation;




    public SystemLocationManager(Context context, LocationChanged locationChanged) {

        if ((ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) || (ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            throw new SecurityException("Didn't got location permissions");
        }

        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if(locationManager==null){
            throw new NullPointerException("Location manager is null");
        }

        List<String> providers = locationManager.getAllProviders();
        if (!providers.contains(LocationManager.GPS_PROVIDER) || !providers.contains(LocationManager.NETWORK_PROVIDER)){
            throw new NullPointerException("No location providers");
        }

        locationManager.requestLocationUpdates(providers.get(1), MIN_UPDATE_INTERVAL_MS, MIN_DISTANCE_CHANGE_UPDATES_M, this);
        this.locationChanged = locationChanged;


    }

    @Nullable
    public  Location getLastLocation(){
        return this.lastLocation;
    }

    @Override
    public void onLocationChanged(Location location) {
        locationChanged.onNewLocation(location);
        lastLocation = location;

    }



    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
