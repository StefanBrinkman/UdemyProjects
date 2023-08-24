package com.example.learnjava;

public class MyClass {
    public static void main(String[] args) {
        Person person = new Person("James", 56);
        System.out.println(person.age);

        Hero superman = new Hero("Superman", 123, 400);
        System.out.println(superman.power);
    }
}

