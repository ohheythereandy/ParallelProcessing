import java.util.concurrent.Semaphore;

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


    public Fork(Semaphore[] s) {

        state = new int[totalPhilosopherCount];
        this.s = s;
    }

    public synchronized void setHungry(int id) {
        state[id] = HUNGRY;
    }

    public synchronized void setThinking(int id) {
        state[id] = THINKING;
    }

    public synchronized void test(Philosopher p){
        if(state[p.id] == HUNGRY && state[p.left] != EATING && state[p.right] != EATING){
            state[p.id] = EATING;
        }
    }

    public synchronized void takeForks(Philosopher p){

        state[p.id] = HUNGRY;
        test(p);
        if(state[p.id] != EATING)
            try {
                System.out.println("Philosopher " + p.name + ": blocked trying to grab forks.");
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


    }

    public synchronized void putForks(Philosopher p) {
        state[p.id] = THINKING;
        this.notify();
    }


}
