package com.example.tesi_gse;

import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tesi_gse.BatchOperation.BatchOperationsManager;
import com.example.tesi_gse.Operation.GPSOperation;
import com.example.tesi_gse.Operation.HTTPOperation;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestClass {

    public TestClass(){

    }

    public static void httpBatch(){

        try{
            for(int j = 0; j < 1000; j++){
                HTTPOperation operation = new HTTPOperation();
                for(int i = 0; i < 24; i++){
                    BatchOperationsManager.getInstance().addOperation(operation);
                    TimeUnit.SECONDS.sleep(15);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void gpsBatch(AppCompatActivity self){
        try {
            for(int j = 0; j < 1000; j++){
                GPSOperation operation = new GPSOperation(self);
                for(int i = 0; i < 24; i++){
                    BatchOperationsManager.getInstance().addOperation(operation);
                    TimeUnit.SECONDS.sleep(15);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void httpNoBatch(){
        try{
            for(int j = 0; j < 1000; j++){
                HTTPOperation operation = new HTTPOperation();
                for(int i = 0; i < 24; i++){
                    operation.execute();
                    TimeUnit.SECONDS.sleep(15);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void gpsNoBatch(AppCompatActivity self){
        try {
            for(int j = 0; j < 1000; j++){
                GPSOperation operation = new GPSOperation(self);
                for(int i = 0; i < 24; i++){
                    operation.execute();
                    TimeUnit.SECONDS.sleep(15);
                }

            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
