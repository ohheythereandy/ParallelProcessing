package javabrains.unit3;

/**
 * Created by HiThereAndy on 9/30/2018.
 */
public class MethodReferenceExample1 {

    public static void main(String[] args) {

        Thread t = new Thread(MethodReferenceExample1::printMessage);
        t.start();

    }

    public static void printMessage() {
        System.out.println("Hello world");
    }
}
