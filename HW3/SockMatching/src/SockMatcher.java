import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by HiThereAndy on 10/7/2018.
 */
public class SockMatcher extends Thread{

    private ArrayList<String> sockQueue;
    private int totalSockCounter;
    private Washer wash;
    public AtomicInteger sockColorsCompleted = new AtomicInteger(0);

    public SockMatcher(Washer wash) {
        sockQueue = new ArrayList<>();
        totalSockCounter = 0;
        this.wash = wash;
        System.out.println("Created SockMatcher thread.");
    }


    public synchronized void addSock(String sockColor){
        sockQueue.add(sockColor);
        totalSockCounter++;
    }

    public void run(){

        System.out.println("starting matching thread");

        //iterate while producing socks
        while(sockColorsCompleted.get() != 4) {
            matchSocks();
        }

        //iterate while done producing socks
        while(!sockQueue.isEmpty()){
            finalMatchSock();
        }
        wash.doneMatching.set(true);
        System.out.println("ending matching thread");
    }

    private void finalMatchSock() {

        String currentColor="";
        int index = 0;


        while(index < sockQueue.size()){

            currentColor = sockQueue.get(index);
            int freqCount = Collections.frequency(sockQueue, currentColor);
            if(freqCount >= 2){

                sockQueue.remove(index);
                sockQueue.remove(currentColor);
                wash.sendSocks(currentColor);
                System.out.println("Matching Thread: Send " + currentColor + "socks to Washer, Total socks " + totalSockCounter + ". Total inside queue " + sockQueue.size());
            } else {
                index++;
            }

        }

        System.out.println("No more pairs to match, emptying queue.");
        sockQueue.clear();

    }

    private synchronized void matchSocks() {

        String currentColor="";
        int index = 0;
        boolean foundSock = false;

        while(!foundSock && index < sockQueue.size()){

            currentColor = sockQueue.get(index);
            int freqCount = Collections.frequency(sockQueue, currentColor);
            if(freqCount >= 2){

                foundSock = true;
                sockQueue.remove(index);
                sockQueue.remove(currentColor);
                wash.sendSocks(currentColor);
                System.out.println("Send " + currentColor + "socks to Washer, Total socks " + totalSockCounter + ". Total inside queue " + sockQueue.size());
            } else {
                index++;
            }

        }

    }

}
