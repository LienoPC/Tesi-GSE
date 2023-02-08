package com.example.tesi_gse.BatchOperation;

import java.util.ArrayList;
import java.util.TimerTask;

/* INVOKER della Command, ha un insieme di richieste che vengono inserite in un ArrayList

 */
@ToBatch
public class BatchOperationsManager extends TimerTask {

    private static BatchOperationsManager instance;
    private ArrayList<BatchOperation> operationSet;

    public static BatchOperationsManager getInstance() {
        if(instance == null){
            instance = new BatchOperationsManager();
        }
        return instance;
    }



    private BatchOperationsManager(){
        operationSet = new ArrayList<>();
    }

    public void addOperation(BatchOperation operation){

        System.out.println("Inserisco l'operazione: " + operation);
        operationSet.add(operation);

        if (operationSet.size() >= 4){
            run();
        }
    }

    @Override
    public void run() {

        try {
            System.out.println(operationSet);
            for(BatchOperation o : operationSet){
                System.out.println(o);
                o.execute();
            }
            operationSet.clear();
            System.out.println(operationSet);
            System.out.println("Uscito dal for\n\n\n");


        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
