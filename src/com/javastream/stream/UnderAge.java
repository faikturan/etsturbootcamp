package com.javastream.stream;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UnderAge {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Ahmet", 19, "T.C"));
        people.add(new Person("Ayşe", 29, "T.C"));
        people.add(new Person("John", 39, "English"));
        people.add(new Person("Smith", 17, "English"));
        people.add(new Person("George", 16, "English"));
        people.add(new Person("George", 17, "English"));

        System.out.println(getKidsName2(people));;
    }

    public static Set<String> getKidsName(List<Person> people){
        Set<String> kids = new HashSet<>();
        for (Person person : people){
            if (person.getAge() < 18){
                kids.add(person.getName());
            }
        }
        return kids;
    }

    public static Set<String> getKidsName2(List<Person> people){
       return people.stream()
               .filter(person -> person.getAge() < 18)
               .map(Person::getName)
               .collect(Collectors.toSet());
    }

}
