package javabrains.unit1;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by HiThereAndy on 9/30/2018.
 */
public class Unit1ExerciseSolutionJava7 {

    public static void main(String[] args){

        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 68),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 39)
        );

        //Step1: sort list by last name
        Collections.sort(people, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });

        //step2: create a method that prints all elements in list
        System.out.println("Printing all persons");
        printAll(people);

        //step3: create a method that prints all people that have last name beginning with C
        System.out.println("Printing all persons with last name beginning with C");
        printConditonally(people, new Condition() {
            public boolean test(Person p) {
                return p.getLastName().startsWith("C");
            }
        });

        System.out.println("Printing all persons with first name beginning with C");
        printConditonally(people, new Condition() {
            public boolean test(Person p) {
                return p.getFirstName().startsWith("C");
            }
        });

    }

    private static void printConditonally(List<Person> people, Condition condition){
        for(Person p: people ) {
            if(condition.test(p)) {
                System.out.println(p);
            }
        }
    }

    private static void printAll(List<Person> people) {
        for(Person p : people){
            System.out.println(p);
        }
    }
}

interface Condition {
    boolean test(Person p);
}
