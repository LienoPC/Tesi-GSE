package BatchOperation;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* INVOKER della Command, ha un insieme di richieste che vengono inserite in un ArrayList

 */
@ToBatch
public class BatchOperationsManager {

    private static BatchOperationsManager instance;
    private ArrayList<BatchOperation> operationSet;

    public static synchronized BatchOperationsManager getInstance() {
        if(instance == null){
            instance = new BatchOperationsManager();
        }
        return instance;
    }



    private BatchOperationsManager(){
        operationSet = new ArrayList<>();
    }

    //Posso aggiungere la politica di esecuzione all'interno del metodo add
    public void addOperation(BatchOperation operation){
        operationSet.add(operation);
    }

    @ToBatchMethod
    public boolean execute() throws java.lang.InterruptedException{
        System.out.println("Invocazione");
        for(BatchOperation o : operationSet){
            System.out.println("Prima dell'esecuzione");
            o.execute();
            System.out.println("Dopo l'esecuzione");
            TimeUnit.SECONDS.sleep(5);

        }
        operationSet.clear();
        return true;

    }
}
