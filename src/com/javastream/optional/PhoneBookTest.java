package com.javastream.optional;


import java.util.Optional;

public class PhoneBookTest {

    private PhoneBook phoneBook;

    public PhoneBookTest(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    public PhoneBook getPhoneBook() {
        return phoneBook;
    }

    public static void main(String[] args) {
        PhoneBookTest p = new PhoneBookTest(new PhoneBook());
        System.out.println(p.findPhoneNumberByNameOrNameByPhoneNumber("Zeynep", "05335568900"));
    }

    public String findPhoneNumberByNameOrNameByPhoneNumber(String name, String phoneNumber){
        Optional<String> phoneNumberOptional = phoneBook.findPhoneByName(name);
        Optional<String> nameOptional = phoneBook.findNameByPhoneNumber(phoneNumber);

        if (phoneNumberOptional.isPresent()){
            return phoneNumberOptional.get();
        }else if(nameOptional.isPresent()){
            return nameOptional.get();
        }

        return phoneBook.findPhoneByName(name)
                .orElse(phoneBook.findNameByPhoneNumber(phoneNumber)
                        .orElse(phoneBook.findPhoneByName("Ahmet")
                                .orElse(null))
                );
    }

    /*public String find (String name,String phoneNumber){
        return getPhoneBook().findNameByPhone(name).orElse(getPhoneBook().findPhoneByName(phoneNumber).
                orElse(null));
    }*/
}
