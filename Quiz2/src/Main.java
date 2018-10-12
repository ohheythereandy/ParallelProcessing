import java.util.concurrent.ExecutionException;

/**
 * Created by HiThereAndy on 10/10/2018.
 */
public class Main {

    public static void main(String[] args) {

        GameOfLife game = new GameOfLife(5, 5);
        try {
            game.main();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
