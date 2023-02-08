package com.example.tesi_gse;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tesi_gse.BatchOperation.BatchOperationsManager;
import com.example.tesi_gse.BatchOperation.Scheduler;
import com.example.tesi_gse.Operation.GPSOperation;
import com.example.tesi_gse.Operation.HTTPOperation;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    View requestBtn;
    private BatchOperationsManager manager;
    private static AppCompatActivity self;

    public static AppCompatActivity getSelf() {
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
        manager = BatchOperationsManager.getInstance();
        setContentView(R.layout.activity_main);
        requestBtn =  findViewById(R.id.requestBtn);
        Scheduler.start();
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    GPSOperation operation1 = new GPSOperation(getSelf());
                    GPSOperation operation2 = new GPSOperation(getSelf());
                    manager.addOperation(operation1);
                    manager.addOperation(operation2);

                    GPSOperation operation3 = new GPSOperation(getSelf());
                    HTTPOperation operation4 = new HTTPOperation();
                    TimeUnit.SECONDS.sleep(50);
                    manager.addOperation(operation3);
                    manager.addOperation(operation4);




                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });

    }
}