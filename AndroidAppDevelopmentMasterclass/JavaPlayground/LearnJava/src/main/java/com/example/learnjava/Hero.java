package com.example.learnjava;

public class Hero extends Person {
    int power;
    public Hero(String name, int age, int power) {
        super(name,age);
        this.power = power;
    }
}
