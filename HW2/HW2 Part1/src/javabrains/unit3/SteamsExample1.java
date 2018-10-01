package javabrains.unit3;

import javabrains.unit1.Person;

import java.util.Arrays;
import java.util.List;

/**
 * Created by HiThereAndy on 9/30/2018.
 */
public class SteamsExample1 {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Charles", "Dickens", 68),
                new Person("Lewis", "Carroll", 42),
                new Person("Thomas", "Carlyle", 51),
                new Person("Charlotte", "Bronte", 45),
                new Person("Matthew", "Arnold", 39)
        );

//        people.stream()
//                .filter(p -> p.getLastName().startsWith("C"))
//                .forEach(p -> System.out.println(p.getFirstName()));

        long count = people.parallelStream()
                .filter(p -> p.getLastName().startsWith("D"))
                .count();

        System.out.println(count);
    }
}
