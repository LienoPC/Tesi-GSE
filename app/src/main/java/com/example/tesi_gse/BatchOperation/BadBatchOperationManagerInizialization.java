package com.example.tesi_gse.BatchOperation;

public class BadBatchOperationManagerInizialization extends Exception{

    @Override
    public void printStackTrace() {
        System.out.println("Bad Inizialization of BatchOperationManager");
    }
}
