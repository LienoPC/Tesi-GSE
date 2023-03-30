package com.example.tesi_gse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    private AppCompatActivity self;

    public AppCompatActivity getSelf() {
        return self;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case GPSOperation.PERMISSIONS_FINE_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                }else {
                    Toast.makeText(this, "Permission needed", Toast.LENGTH_SHORT).show();
                }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self = this;
        setContentView(R.layout.activity_main);
        requestBtn =  findViewById(R.id.requestBtn);
        requestHttp = findViewById(R.id.requestHttp);
        noBatchGps = findViewById(R.id.requestGpsNobatch);
        noBatchHttp = findViewById(R.id.requestHttpNobatch);
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