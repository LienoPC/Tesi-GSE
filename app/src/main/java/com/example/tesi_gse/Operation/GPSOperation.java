package com.example.tesi_gse.Operation;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.tesi_gse.BatchOperation.BatchOperation;
import com.example.tesi_gse.MainActivity;
import com.example.tesi_gse.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.Priority;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnSuccessListener;


public class GPSOperation implements BatchOperation {

    public static final int PERMISSIONS_FINE_LOCATION = 99;
    //Google's API for location services
    private FusedLocationProviderClient fusedLocationProviderClient;

    private AppCompatActivity context;
    public GPSOperation(AppCompatActivity context){
        this.context = context;
    }


    @Override
    public void execute() {
        //Get permission from the user to use GPS
        CancellationTokenSource token = new CancellationTokenSource();
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            OnSuccessListener onSuccessListener = new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    Log.d(TAG, "onSuccessGps: Thread Id "+ Thread.currentThread().getId());
                    System.out.println("Faccio la locazione");
                    writeValues(location);
                }
            };
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);

            fusedLocationProviderClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, token.getToken()).addOnSuccessListener(onSuccessListener);
        }



    }

    private void writeValues(Location location){
        System.out.println(location);
        System.out.println("Prelevata posizione");


    }
}
