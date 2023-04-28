package com.example.tesi_gse.BatchOperation;

import android.os.Handler;

import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Scheduler implements Runnable{

    private final long PERIOD;
    private final long DELAY;

    private ScheduledExecutorService schedule;

    public Scheduler(long DELAY, long PERIOD){
        super();
        this.DELAY = DELAY;
        this.PERIOD = PERIOD;

    }

    //Metodo che si occupa della politica delle operazioni
    public void run(){
            try {
                TimerTask batchOperations = BatchOperationsManager.getInstance(null);
                schedule = Executors.newSingleThreadScheduledExecutor();
                schedule.scheduleAtFixedRate(batchOperations, DELAY, PERIOD, TimeUnit.SECONDS);
            }catch (Exception e){
                e.printStackTrace();
            }
    }




    public void stop(){
        schedule.shutdown();
    }
    /*
    Metodo che trova l'execute della command tramite annotazioni
    Non più utile con l'uso del TimerTask
    public static void execute(Object obj) throws NoSuchMethodException{

        try{
            Class<?> cl = obj.getClass();
            System.out.println(cl);
            if(cl.isAnnotationPresent(ToBatch.class)){

                for(Method method : cl.getMethods()){
                    System.out.println(cl.getMethods());
                    if(method.isAnnotationPresent(ToBatchMethod.class)){
                        method.setAccessible(true);
                        method.invoke(obj);
                    }
                }
            }else{
                //Throw di errore
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    */

}
