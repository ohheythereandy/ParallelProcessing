import java.util.Random;

/**
 * Created by HiThereAndy on 10/7/2018.
 */
public class Main {

    public static void main(String[] args) {
        //System.out.println("Hello World!");

        Washer wash = new Washer();
        SockMatcher sm = new SockMatcher(wash);
        Sock t1 = new Sock("Blue", sm,getRandomNumber());
        Sock t2 = new Sock("Red", sm, getRandomNumber());
        Sock t3 = new Sock("Green", sm,getRandomNumber());
        Sock t4 = new Sock("Orange", sm,getRandomNumber());


        t1.start();
        t2.start();
        t3.start();
        t4.start();
        sm.start();
        wash.start();



    }

    public static int getRandomNumber(){
        Random rand = new Random();
        return rand.nextInt(100) + 1;
    }
}
