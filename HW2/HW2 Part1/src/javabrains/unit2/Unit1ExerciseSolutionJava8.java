package javabrains.unit2;

import javabrains.unit1.Person;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Created by HiThereAndy on 9/30/2018.
 */
public class Unit1ExerciseSolutionJava8 {

    public static void main(String[] args){

        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 68),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 39)
        );

        //Step1: sort list by last name
        Collections.sort(people, (p1, p2) -> p1.getLastName().compareTo(p2.getLastName()));

        //step2: create a method that prints all elements in list
        System.out.println("Printing all persons");
        performConditionally(people, p -> (true), p -> System.out.println(p));

        //step3: create a method that prints all people that have last name beginning with C
        System.out.println("Printing all persons with last name beginning with C");
        performConditionally(people, p -> p.getLastName().startsWith("C"),  p -> System.out.println(p));

        System.out.println("Printing all persons with first name beginning with C");
        performConditionally(people, p -> p.getFirstName().startsWith("C"),  p -> System.out.println(p.getFirstName()));


    }

    private static void performConditionally(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer){
        for(Person p: people ) {
            if(predicate.test(p)) {
                consumer.accept(p);
            }
        }
    }

    private static void printAll(List<Person> people) {
        for(Person p : people){
            System.out.println(p);
        }
    }
}

