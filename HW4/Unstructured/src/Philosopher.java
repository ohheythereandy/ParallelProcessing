import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * Created by HiThereAndy on 10/11/2018.
 */
public class Philosopher extends Thread{

    final static int THINKING = 0;
    final static int HUNGRY = 1;
    final static int EATING = 2;
    final static int totalPhilosopherCount = 5;

    boolean isFull;
    String name;
    int left, right, id;
    Random rand;
    Fork fork;



    public Philosopher(String name, int id, Fork fork) {
        this.name = name;
        isFull = false;
        left = (id + totalPhilosopherCount - 1) % totalPhilosopherCount;
        right = (id + 1) % totalPhilosopherCount;
        this.id = id;
        this.fork = fork;

        rand = new Random();
    }

    public void run() {

        while(!isFull) {
            think();
            System.out.println("Philosopher " + name + ": attempt to acquire forks.");

            fork.takeForks(this);

            System.out.println("Philosopher " + name + ": acquired forks.");
            eat();

            fork.putForks(this);

            System.out.println("Philosopher " + name + ": put down forks.");
        }
    }




    private void eat() {
        try {
            int timeToWait = rand.nextInt(10) + 1;
            System.out.println("Philosopher " + name + ": Eating for " + timeToWait + " seconds.");
            Thread.sleep(timeToWait * 1000);
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }

    }

    private void think() {
        try {
            int timeToWait = rand.nextInt(5) + 1;
            System.out.println("Philosopher " + name + ": Thinking for " + timeToWait + " seconds.");
            Thread.sleep(timeToWait * 1000);
        }
        catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
