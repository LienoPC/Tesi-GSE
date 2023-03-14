package com.example.tesi_gse;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.os.HandlerCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.example.tesi_gse.BatchOperation.BatchOperationsManager;
import com.example.tesi_gse.BatchOperation.Scheduler;
import com.example.tesi_gse.Operation.GPSOperation;
import com.example.tesi_gse.Operation.HTTPOperation;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    private final static int SCH_ID = 1000;
    View requestBtn;
    View requestHttp;
    View noBatchHttp;
    View noBatchGps;
    private Scheduler scheduler;
    private ActivityResultLauncher<String> requestPermissionLauncher;
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    private AppCompatActivity self;

    public AppCompatActivity getSelf() {
        return self;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Funonzia", Toast.LENGTH_SHORT).show();
        self = this;
        setContentView(R.layout.activity_main);
        requestBtn =  findViewById(R.id.requestBtn);
        requestHttp = findViewById(R.id.requestHttp);
        noBatchGps = findViewById(R.id.requestGpsNobatch);
        noBatchHttp = findViewById(R.id.requestHttpNobatch);
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if(permissionCheck != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)){
                Toast.makeText(this, "GPS Needed", Toast.LENGTH_SHORT).show();
            }else{
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            }
        }
/*
        requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
            if (isGranted) {
                Toast.makeText(this, "Funonzia", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "GPS Needed", Toast.LENGTH_SHORT).show();
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            }
        });
        */
/*
        scheduler = new Scheduler(new Executor() {
            @Override
            public void execute(Runnable runnable) {
                runnable.run();
            }
        });
        scheduler.start();
*/


        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scheduler = new Scheduler();
                executorService.execute(scheduler);
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        TestClass.gpsBatch(self);
                        Toast.makeText(self,"Finito GpsBatch", Toast.LENGTH_SHORT).show();
                    }
                });


                /*
                try {
                    for(int j = 0; j < 50; j++){
                        GPSOperation operation = new GPSOperation(self);
                        for(int i = 0; i < 500; i++){
                            manager.addOperation(operation);
                        }
                        TimeUnit.SECONDS.sleep(120);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                */

            }
        });
        requestHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scheduler = new Scheduler();
                executorService.execute(scheduler);
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        TestClass.httpBatch();
                        Toast.makeText(null,"Finito HttpBatch", Toast.LENGTH_SHORT).show();
                    }
                });

                /*
                try{
                    for(int j = 0; j < 50; j++){
                        HTTPOperation operation = new HTTPOperation();
                        for(int i = 0; i < 500; i++){
                            manager.addOperation(operation);
                        }
                        TimeUnit.SECONDS.sleep(120);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                */

            }
        });

        noBatchGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        TestClass.gpsNoBatch(self);
                        Toast.makeText(self,"Finito GpsNoBatch", Toast.LENGTH_SHORT).show();
                    }
                });


                /*
                try {
                    for(int j = 0; j < 50; j++){
                        GPSOperation operation = new GPSOperation(self);
                        for(int i = 0; i < 500; i++){
                            operation.execute();
                        }
                        TimeUnit.SECONDS.sleep(120);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
                */

            }
        });

        noBatchHttp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        TestClass.httpNoBatch();
                        Toast.makeText(null,"Finito HttpNoBatch", Toast.LENGTH_SHORT).show();
                    }
                });

                /*
                try{
                    for(int j = 0; j < 50; j++){
                        HTTPOperation operation = new HTTPOperation();
                        for(int i = 0; i < 500; i++){
                            operation.execute();
                            TimeUnit.MILLISECONDS.sleep(10);
                        }
                        TimeUnit.SECONDS.sleep(120);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                 */
            }
        });


    }
}