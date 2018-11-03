import java.util.ArrayList;

/**
 * Created by Andy on 11/3/18.
 */
public class BoundedBuffer {

    private ArrayList<Integer> itemList;
    private final int MAX_CAPACITY = 10;
    private int ID;

    public BoundedBuffer(int ID) {
        itemList = new ArrayList<>();
        this.ID = ID;
    }

    public synchronized boolean isEmpty() {
        return itemList.isEmpty();
    }

    public synchronized int remove() {

        //check if there are items to remove
        while(itemList.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //items were added, now we can remove
        int ret = itemList.remove(0);

        notify();

        return ret;
    }


    public synchronized void insert(int value) {

        //check to make sure there is space in buffer
        while(itemList.size() == MAX_CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        //item was removed, now we can insert
        itemList.add(value);
        notify();
    }

}
