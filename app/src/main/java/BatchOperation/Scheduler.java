package BatchOperation;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class Scheduler {


    //Metodo che si occupa della politica delle operazioni
    public static void start(){

        try {

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    //Metodo che trova l'execute della command tramite annotazioni
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
}
