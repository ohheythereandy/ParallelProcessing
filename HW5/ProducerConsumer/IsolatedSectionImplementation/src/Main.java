import java.util.Scanner;

/**
 * Created by Andy on 11/3/18.
 */
public class Main {

    public static void main(String[] args) throws InterruptedException{

        Scanner kb = new Scanner(System.in);
        BoundedBuffer sharedBuffer = new BoundedBuffer(1);
        long startTime;
        long endTime;

        System.out.println("Welcome to Producer/Consumer using Locks implementation.");
        System.out.print("Would you like to use \n1) 5 Producers and 2 Consumers or\n2) 2 Producers and 5 Consumers");
        int option = kb.nextInt();

        if(option == 1) {

            Producer p1 = new Producer(sharedBuffer, 1);
            Producer p2 = new Producer(sharedBuffer, 2);
            Producer p3 = new Producer(sharedBuffer, 3);
            Producer p4 = new Producer(sharedBuffer, 4);
            Producer p5 = new Producer(sharedBuffer, 5);

            Consumer c1 = new Consumer(sharedBuffer, 1);
            Consumer c2 = new Consumer(sharedBuffer, 2);

            startTime = System.currentTimeMillis();

            p1.start();
            p2.start();
            p3.start();
            p4.start();
            p5.start();

            c1.start();
            c2.start();

            c1.join();
            c2.join();

            endTime = System.currentTimeMillis();
            double totalTime = (endTime - startTime) / 1000;
            System.out.println("Finished execution of 5 Producers and 2 Consumers in: " + totalTime + " seconds.");

        } else {

            Producer p1 = new Producer(sharedBuffer, 1);
            Producer p2 = new Producer(sharedBuffer, 2);

            Consumer c1 = new Consumer(sharedBuffer, 1);
            Consumer c2 = new Consumer(sharedBuffer, 2);
            Consumer c3 = new Consumer(sharedBuffer, 3);
            Consumer c4 = new Consumer(sharedBuffer, 4);
            Consumer c5 = new Consumer(sharedBuffer, 5);

            startTime = System.currentTimeMillis();

            p1.start();
            p2.start();


            c1.start();
            c2.start();
            c3.start();
            c4.start();
            c5.start();

            c1.join();
            c2.join();
            c3.join();
            c4.join();
            c5.join();

            endTime = System.currentTimeMillis();
            double totalTime = (endTime - startTime) / 1000;
            System.out.println("Finished execution of 2 Producers and 5 Consumers in: " + totalTime + " seconds.");

        }

    }
}
