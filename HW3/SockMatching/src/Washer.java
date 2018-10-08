import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by HiThereAndy on 10/7/2018.
 */
public class Washer extends Thread{

    private ArrayList<String> sockQueue;
    public AtomicBoolean doneMatching = new AtomicBoolean(false);

    public Washer() {
        sockQueue = new ArrayList<>();
        System.out.println("Created washer thread.");
    }
    public void run(){

        System.out.println("starting washing thread");

        destroySocks();

        System.out.println("ending washing thread");

    }

    private void destroySocks() {

        while(!doneMatching.get()){

            if(!sockQueue.isEmpty()){
                String currentSock = sockQueue.remove(0);
                System.out.println("Washing Thread: Destroyed " + currentSock + "socks.");
            }

        }

        while(!sockQueue.isEmpty()) {
            String currentSock = sockQueue.remove(0);
            System.out.println("Washing Thread: Destroyed " + currentSock + "socks.");
        }
    }

    public synchronized void sendSocks(String currentColor) {

        sockQueue.add(currentColor);
    }
}
