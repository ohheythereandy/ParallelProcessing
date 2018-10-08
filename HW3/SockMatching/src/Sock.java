/**
 * Created by HiThereAndy on 10/7/2018.
 */
public class Sock extends Thread {

    private String sockColor;
    private int socksToProduce;
    private int socksProduced;
    private SockMatcher sm;

    public Sock(String sockColor, SockMatcher sm,int socksToProduce){
        this.sockColor = sockColor;
        this.socksToProduce = socksToProduce;
        this.socksProduced = 0;
        this.sm = sm;
        System.out.println("Created " + sockColor + " sock thread. Socks to produce:" + socksToProduce);
    }

    public void run(){

        System.out.println("starting " + sockColor + " thread");
        while(socksProduced != socksToProduce){
            produceSocks();
        }
        sm.sockColorsCompleted.incrementAndGet();
        System.out.println("ending " + sockColor + " thread");
    }

    private void produceSocks() {

        sm.addSock(sockColor);
        socksProduced++;
        System.out.println(printSockThreadName() + ": Produced " + socksProduced + " of " + socksToProduce + " " + sockColor + " Socks");
    }

    private String printSockThreadName(){
        return sockColor + " Sock";
    }

}
