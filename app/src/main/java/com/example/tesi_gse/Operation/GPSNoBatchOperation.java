package com.example.tesi_gse.Operation;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.tesi_gse.BatchOperation.BatchOperation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnSuccessListener;


public class GPSNoBatchOperation {

    public static final int PERMISSIONS_FINE_LOCATION = 100;

    OnSuccessListener onSuccessListener = new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
            System.out.println(location);
            //writeValues(location);
        }
    };
    Criteria criteria;
    /*
    final LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            Log.d("Location Changes", location.toString());
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.d("Status Changed", String.valueOf(status));
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.d("Provider Enabled", provider);
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.d("Provider Disabled", provider);
        }

    };

     */

    //final LocationManager locationManager;
    private AppCompatActivity context;

    //Google's API for location services
    private FusedLocationProviderClient fusedLocationProviderClient;

    public GPSNoBatchOperation(AppCompatActivity context){
        this.context = context;
    }
/*


    public GPSOperation(AppCompatActivity context){

        criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_HIGH);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(true);
        criteria.setHorizontalAccuracy(Criteria.ACCURACY_HIGH);
        criteria.setVerticalAccuracy(Criteria.ACCURACY_HIGH);
        this.context = context;

        locationManager = (LocationManager)context.getSystemService(context.LOCATION_SERVICE);

    }

    @Override
    public void execute(){

        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            locationManager.requestSingleUpdate(criteria,locationListener, null);
        }else{

        }

    }
*/
    public void execute() {
        //Get permission from the user to use GPS
        CancellationTokenSource token = new CancellationTokenSource();
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

            fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, token.getToken()).addOnSuccessListener(onSuccessListener);
            System.out.println("Dopo il get");

        }



    }

    private void writeValues(Location location){
        System.out.println(location.toString());
    }


}
