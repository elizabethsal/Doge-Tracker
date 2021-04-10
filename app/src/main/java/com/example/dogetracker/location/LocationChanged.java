package com.example.dogetracker.location;


import android.location.Location;

public interface LocationChanged {

    void onNewLocation(Location location);

}
