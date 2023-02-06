package BatchOperation;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/* INVOKER della Command, ha un insieme di richieste che vengono inserite in un ArrayList

 */
@ToBatch
public class BatchOperationsManager extends TimerTask {

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

    @Override
    public void run() {

        try {
            for(BatchOperation o : operationSet){
                o.execute();
                TimeUnit.SECONDS.sleep(5);

            }
            operationSet.clear();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
