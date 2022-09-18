package com.javastream.optional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PhoneBook {

    private static final HashMap<String, String> PHONE_NUMBERS = new HashMap<String, String>(){
        {
            put("Ahmet", "05555568900"); //null NullPointerException
            put("Mehmet", "05445568900");
            put("Ali", "05335568900");
        }
    };

    private HashMap<String, String> phoneBookEntries = PHONE_NUMBERS;

    public PhoneBook() {
    }

    public HashMap<String, String> getPhoneBookEntries(){
            return phoneBookEntries;
    }

    public Optional<String> findPhoneByName(String name){
        return Optional.ofNullable(PHONE_NUMBERS.get(name));
    }

    public Optional<String> findNameByPhoneNumber(String phoneNumber){
        return PHONE_NUMBERS.entrySet()
                .stream()
                .filter(entry ->entry.getValue().equals(phoneNumber))
                .map(Map.Entry::getKey)
                .findFirst();
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "phoneBookEntries=" + phoneBookEntries +
                '}';
    }
}
