package com.example.tesi_gse.Operation;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.tesi_gse.BatchOperation.BatchOperation;

import java.io.IOException;


import okhttp3.*;


public class HTTPOperation implements BatchOperation {

    private OkHttpClient client;

    public HTTPOperation(OkHttpClient client){
        this.client = client;
    }
    @Override
    public void execute() {
        Request request = new Request.Builder().url("https://publicobject.com/helloworld.txt").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d(TAG, "onResponseHttp: Thread Id "+ Thread.currentThread().getId());
                try{
                    if(response.isSuccessful()){
                        ResponseBody responseBody = response.body();
                        String body = responseBody.string();
                        System.out.println("Comunicazione avvenuta");
                    }else{
                        System.out.println("Unespected code" + response);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
    }
}
