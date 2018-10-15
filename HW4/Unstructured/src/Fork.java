import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by HiThereAndy on 10/11/2018.
 */
public class Fork {

    final static int THINKING = 0;
    final static int HUNGRY = 1;
    final static int EATING = 2;
    final static int totalPhilosopherCount = 5;

    int[] state;
    Semaphore[] s;
    final Lock lock = new ReentrantLock();
    final Condition notAvailable = lock.newCondition();

    public Fork() {

        state = new int[totalPhilosopherCount];

    }


    public void test(Philosopher p){
        if(state[p.id] == HUNGRY && state[p.left] != EATING && state[p.right] != EATING){
            state[p.id] = EATING;
        }
    }

    public void takeForks(Philosopher p){

        lock.lock();
        try {
            state[p.id] = HUNGRY;
            test(p);
            if(state[p.id] != EATING)
                try {
                    System.out.println("Philosopher " + p.name + ": blocked trying to grab forks.");
                    notAvailable.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

        } finally {
            lock.unlock();
        }


    }

    public void putForks(Philosopher p) {
        lock.lock();
        try {
            state[p.id] = THINKING;
            notAvailable.signal();
        } finally {
            lock.unlock();
        }
    }


}
