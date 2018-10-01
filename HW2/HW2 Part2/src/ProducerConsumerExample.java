/**
 * Created by HiThereAndy on 9/30/2018.
 */
public class ProducerConsumerExample {

    public static void main(String[] args){
        Drop drop = new Drop();
        (new Thread(new Producer(drop))).start();
        (new Thread(new Consumer(drop))).start();

    }
}
