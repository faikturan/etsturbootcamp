package com.javastream.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxAndComparator {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Ahmet", 19, "T.C"));
        people.add(new Person("Ayşe", 29, "T.C"));
        people.add(new Person("John", 39, "English"));
        people.add(new Person("Smith", 17, "English"));
        people.add(new Person("George", 16, "English"));

        System.out.println(getOldestPerson2(people));;
    }



        public static Person getOldestPerson(List<Person> people){
            Person oldestPerson = new Person("", 0);
            for (Person person: people){
                if (person.getAge() > oldestPerson.getAge()){
                    oldestPerson = person;
                }
            }
            return oldestPerson;
        }

    public static Person getOldestPerson2(List<Person> people){
       return people.stream()
               .max(Comparator.comparingInt(Person::getAge))
               .orElse(null);
    }



}
