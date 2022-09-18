package com.javastream.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class partitionAdults {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Ahmet", 19, "T.C"));
        people.add(new Person("Ayşe", 29, "T.C"));
        people.add(new Person("John", 39, "English"));
        people.add(new Person("Smith", 17, "English"));
        people.add(new Person("George", 16, "English"));

        System.out.println(partitionAdults2(people));
    }

    public static Map<Boolean, List<Person>> partitionAdults(List<Person> people){
        Map<Boolean, List<Person>> map = new HashMap<>();
        map.put(true, new ArrayList<>());
        map.put(false, new ArrayList<>());
        for (Person person : people){
            map.get(person.getAge() >= 18).add(person);
        }
        return map;
    }

    //List<Integer> : 1,2,3,4,5,67,8
    public static Map<Boolean, List<Person>> partitionAdults2(List<Person> people){
       return people.stream()
               .collect(Collectors.groupingBy(person -> person.getAge() >= 18,
                       Collectors.toList()));
    }



}
