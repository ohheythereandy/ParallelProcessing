package javabrains.unit1;

/**
 * Created by HiThereAndy on 9/30/2018.
 */
public class Greeter {

    public void greet(Greeting greeting) {
        greeting.perform();
    }
    public static void main(String[] args){

        Greeter greeter = new Greeter();

        Greeting lambdaGreeting = () -> System.out.print("Hello world!");

        Greeting innerClassGreeting = new Greeting() {
            public void perform() {
                System.out.print("Hello world!");
            }
        };


        greeter.greet(lambdaGreeting);
        greeter.greet(innerClassGreeting);
    }
}
