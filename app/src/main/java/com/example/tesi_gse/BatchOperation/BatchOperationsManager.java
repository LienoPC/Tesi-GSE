package com.example.tesi_gse.BatchOperation;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.tesi_gse.Operation.GPSOperation;

import com.example.tesi_gse.Operation.HTTPOperation;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;
import java.util.TimerTask;

import okhttp3.OkHttpClient;

public class BatchOperationsManager extends TimerTask {

    private static BatchOperationsManager instance;
    private ArrayList<BatchOperation> gpsOperationSet;
    private ArrayList<BatchOperation> httpOperationSet;
    AppCompatActivity context;

    private final OkHttpClient client = new OkHttpClient();


    private FusedLocationProviderClient fusedLocationProviderClient;



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
        this.context = context;

    }

    public void addOperation(BatchOperation operation){

        System.out.println("Inserisco l'operazione: " + operation);
        if(operation instanceof GPSOperation){
            gpsOperationSet.add(operation);
        }
        if(operation instanceof HTTPOperation){
            httpOperationSet.add(operation);
        }


    }

    @Override
    public void run() {

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
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

    public FusedLocationProviderClient getFusedLocationProviderClient() {
        return fusedLocationProviderClient;
    }
}
