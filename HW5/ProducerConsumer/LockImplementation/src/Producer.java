import java.util.Random;

/**
 * Created by Andy on 11/3/18.
 */
public class Producer extends Thread {

    private BoundedBuffer itemBuffer;
    private int ID;
    private int[] itemsToProduce;
    private final int producerLimit = 100;

    public Producer(BoundedBuffer itemBuffer, int ID) {

        this.itemBuffer = itemBuffer;
        this.ID = ID;
        itemsToProduce = new int[producerLimit];

    }

    public void run() {

        //populate itemsToProduce array
        getRandomItems();

        //produce items into buffer
        for(int i = 0; i < producerLimit; i++) {

            itemBuffer.insert(itemsToProduce[i]);
            System.out.println("Producer " + ID + " placed " + itemsToProduce[i] + " into the buffer." );
        }

        //indicate that we are done producing
        System.out.println("Producer " + ID + " has finished producing items.");

    }

    private void getRandomItems() {

        Random rand = new Random();

        for(int i = 0; i < producerLimit; i++) {
            itemsToProduce[i] = rand.nextInt(1000);
        }

    }
}
