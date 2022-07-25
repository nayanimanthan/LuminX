package com.example.luminx;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;

import androidx.core.content.ContextCompat;

public class GPSTracker extends Service implements LocationListener {
    private static final long MIN_TIME_BW_UPDATES = 60000;
    private boolean canGetLocation = false;
    private boolean isGPSEnabled = false;
    private boolean isNetworkEnabled = false;
    private double latitude;
    private Location location;
    protected LocationManager locationManager;
    private double longitude;
    private final Context mContext;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onLocationChanged(Location location2) {
    }

    public void onProviderDisabled(String str) {
    }

    public void onProviderEnabled(String str) {
    }

    public void onStatusChanged(String str, int i, Bundle bundle) {
    }



    public GPSTracker(Context context) {
        this.mContext = context;
        getLocation();
    }

    public Location getLocation() {
        String str3 = "network";
        String str4 = "gps";
        try {
            this.locationManager = (LocationManager) this.mContext.getSystemService("location");
            this.isGPSEnabled = this.locationManager.isProviderEnabled(str4);
            this.isNetworkEnabled = this.locationManager.isProviderEnabled(str3);
            if (!this.isGPSEnabled && !this.isNetworkEnabled) {
                return this.location;
            }
            this.canGetLocation = true;
            if (this.isNetworkEnabled) {
                if (ContextCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_FINE_LOCATION") != 0 && ContextCompat.checkSelfPermission(this.mContext, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
                    return null;
                }
                this.locationManager.requestLocationUpdates("network", 0, 0.0f, this);

                if (this.locationManager != null) {
                    this.location = this.locationManager.getLastKnownLocation(str3);
                    if (this.location != null) {
                        this.latitude = this.location.getLatitude();
                        this.longitude = this.location.getLongitude();
                    }
                }
            }
            if (this.isGPSEnabled && this.location == null) {
                this.locationManager.requestLocationUpdates("gps", MIN_TIME_BW_UPDATES, 10.0f, this);

                if (this.locationManager != null) {
                    this.location = this.locationManager.getLastKnownLocation(str4);
                    if (this.location != null) {
                        this.latitude = this.location.getLatitude();
                        this.longitude = this.location.getLongitude();
                    }
                }
            }
            return this.location;
        } catch (Exception e) {
            e.printStackTrace();
            return this.location;
        }
    }


    public double getLatitude() {
        Location location2 = this.location;
        if (location2 != null) {
            this.latitude = location2.getLatitude();
        }
        return this.latitude;
    }

    public double getLongitude() {
        Location location2 = this.location;
        if (location2 != null) {
            this.longitude = location2.getLongitude();
        }
        return this.longitude;
    }

    public boolean canGetLocation() {
        return this.canGetLocation;
    }

    public boolean isgpsenabled() {
        return this.isGPSEnabled;
    }
}
