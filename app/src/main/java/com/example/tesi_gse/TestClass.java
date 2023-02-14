package com.example.tesi_gse;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tesi_gse.BatchOperation.BatchOperationsManager;
import com.example.tesi_gse.Operation.GPSOperation;
import com.example.tesi_gse.Operation.HTTPOperation;

import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestClass {

    private final Executor executor;
    private ScheduledExecutorService schedule;

    public TestClass(Executor executor){
        this.executor = executor;
    }

    public void httpBatch(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try{
                    for(int j = 0; j < 50; j++){
                        HTTPOperation operation = new HTTPOperation();
                        for(int i = 0; i < 500; i++){
                            BatchOperationsManager.getInstance().addOperation(operation);
                        }
                        TimeUnit.SECONDS.sleep(120);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void gpsBatch(AppCompatActivity self){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int j = 0; j < 50; j++){
                        GPSOperation operation = new GPSOperation(self);
                        for(int i = 0; i < 500; i++){
                            BatchOperationsManager.getInstance().addOperation(operation);
                        }
                        TimeUnit.SECONDS.sleep(120);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void httpNoBatch(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
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
            }
        });

    }

    public void gpsNoBatch(AppCompatActivity self){
        executor.execute(new Runnable() {
            @Override
            public void run() {
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
            }
        });
    }
}
