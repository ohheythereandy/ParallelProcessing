package javabrains.unit2;

/**
 * Created by HiThereAndy on 9/30/2018.
 */
public class ClosuresExample {

    public static void main(String[] args){
        int a =10;
        int b = 20;
        doProcess(a, (i -> System.out.println(i + b)));

    }

    public static void doProcess(int i, Process p) {
        p.process(i);
    }

}

interface Process {
    void process(int i);
}
