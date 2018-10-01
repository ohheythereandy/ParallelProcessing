package javabrains.unit3;

import javabrains.unit1.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by HiThereAndy on 9/30/2018.
 */
public class MethodReferenceExample2 {

    public static void main(String[] args) {

        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 68),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 39)
        );

        //step2: create a method that prints all elements in list
        System.out.println("Printing all persons");
        performConditionally(people, p -> (true), System.out::println);


    }

    private static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer){
        for(Person p: people ) {
            if(predicate.test(p)) {
                consumer.accept(p);
            }
        }
    }
}