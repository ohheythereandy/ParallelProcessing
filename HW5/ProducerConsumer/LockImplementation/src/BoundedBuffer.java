import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Andy on 11/3/18.
 */
public class BoundedBuffer {

    private ArrayList<Integer> itemList;
    private final int MAX_CAPACITY = 10;
    private int ID;

    //use lock and condition to handle mutual exclusion
    final Lock lock = new ReentrantLock();
    final Condition isEmpty = lock.newCondition();
    final Condition isFull = lock.newCondition();

    public BoundedBuffer(int ID) {
        itemList = new ArrayList<>();
        this.ID = ID;
    }

    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    public int remove() {

        lock.lock();
        try {
            //check if there are items to remove
            while(itemList.isEmpty()) {
                try {
                    isEmpty.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


            //items were added, now we can remove
            int ret = itemList.remove(0);

            isFull.signal();

            return ret;

        }finally {
            lock.unlock();
        }

    }


    public void insert(int value) {

        lock.lock();
        try {

            //check to make sure there is space in buffer
            while(itemList.size() >= MAX_CAPACITY) {
                try {
                    isFull.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //item was removed, now we can insert
            itemList.add(value);
            isEmpty.signal();

        } finally {
            lock.unlock();
        }

    }

}
