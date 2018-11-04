/**
 * Created by Andy on 11/3/18.
 */
public class Consumer extends Thread{

    private BoundedBuffer itemBuffer;
    private int itemConsuming;
    private int ID;

    public Consumer(BoundedBuffer itemBuffer, int ID) {
        this.itemBuffer = itemBuffer;
        this.ID = ID;
        itemConsuming = 0;
    }

    public void run() {

        //while there are still items to consume
        while(!itemBuffer.isEmpty()) {
            itemConsuming = itemBuffer.remove();
            System.out.println("Consumer " + ID + " took " + itemConsuming + " from the buffer.");

            //sleep for 1 second to simulate consuming
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Consumer " + ID + " has finished consuming items.");
    }

}
