/**
 * Created by HiThereAndy on 9/30/2018.
 */
public class TypeInferenceExample {

    public static void main(String[] args){
        printLambda(s -> s.length());
    }

    public static void printLambda(StringLengthLambda l) {
        System.out.print(l.getLength("Hello lambda"));
    }
    interface StringLengthLambda {
        int getLength(String S);
    }

}
