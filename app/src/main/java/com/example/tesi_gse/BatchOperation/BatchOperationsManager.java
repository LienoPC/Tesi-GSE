package com.example.tesi_gse.BatchOperation;

import android.location.Location;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tesi_gse.Operation.GPSNoBatchOperation;
import com.example.tesi_gse.Operation.HTTPNBatchOperation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.TimerTask;

import okhttp3.OkHttpClient;

/* INVOKER della Command, ha un insieme di richieste che vengono inserite in un ArrayList

 */
public class BatchOperationsManager extends TimerTask {

    private static BatchOperationsManager instance;
    private ArrayList<BatchOperation> gpsOperationSet;
    private ArrayList<BatchOperation> httpOperationSet;

    private final OkHttpClient client = new OkHttpClient();
    private final OnSuccessListener onSuccessListener = new OnSuccessListener<Location>() {
        @Override
        public void onSuccess(Location location) {
            System.out.println(location);
            //writeValues(location);
        }
    };

    private final FusedLocationProviderClient fusedLocationProviderClient;

    private final CancellationTokenSource token = new CancellationTokenSource();


    public static BatchOperationsManager getInstance(@Nullable AppCompatActivity context) {
        if(instance == null){
            instance = new BatchOperationsManager(context);
        }
        return instance;
    }



    private BatchOperationsManager(AppCompatActivity context){
        gpsOperationSet = new ArrayList<>();
        httpOperationSet = new ArrayList<>();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);


    }

    public void addOperation(BatchOperation operation){

        System.out.println("Inserisco l'operazione: " + operation);
        if(operation instanceof GPSNoBatchOperation){
            gpsOperationSet.add(operation);
        }
        if(operation instanceof HTTPNBatchOperation){
            httpOperationSet.add(operation);
        }


    }

    @Override
    public void run() {

        try {
            if(gpsOperationSet.size() > httpOperationSet.size()){
                executeGPS();
                executeHTTP();
            }else{
                executeHTTP();
                executeGPS();
            }
            System.out.println("Eseguito la schedule");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void executeGPS(){
        for(BatchOperation o : gpsOperationSet){
            o.execute();
        }
        gpsOperationSet.clear();
    }

    private void executeHTTP(){
        for(BatchOperation o : httpOperationSet){
            o.execute();
        }
        httpOperationSet.clear();
    }

    public OkHttpClient getClient() {
        return client;
    }

    public OnSuccessListener getOnSuccessListener() {
        return onSuccessListener;
    }

    public FusedLocationProviderClient getFusedLocationProviderClient() {
        return fusedLocationProviderClient;
    }

    public CancellationTokenSource getToken() {
        return token;
    }
}
