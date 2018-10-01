/**
 * Created by HiThereAndy on 9/30/2018.
 */
public class Greeter {

    public void greet(Greeting greeting) {
        greeting.perform();
    }
    public static void main(String[] args){

        Greeter greeter = new Greeter();
        HelloWorldGreeting helloWorldGreeting = new HelloWorldGreeting();
        greeter.greet(helloWorldGreeting);

        Greeting myLambdaFunction = () -> System.out.print("Hello world!");
    }
}
