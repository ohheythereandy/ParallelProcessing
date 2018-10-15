
/**
 * Created by HiThereAndy on 10/11/2018.
 */
public class Main {

    final static int totalPhilosopherCount = 5;

    public static void main(String[] args) {




        Fork fork = new Fork();
        Philosopher a = new Philosopher("A", 0, fork);
        Philosopher b = new Philosopher("B", 1, fork);
        Philosopher c = new Philosopher("C", 2, fork);
        Philosopher d = new Philosopher("D", 3, fork);
        Philosopher e = new Philosopher("E", 4, fork);

        a.start();
        b.start();
        c.start();
        d.start();
        e.start();

    }
}