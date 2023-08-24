package com.example.learnjava;

public class Microphone {
    // instance variables / properties / fields
    String name;
    String color;
    int model;

    public Microphone(String name, String color, int model) {
        this.name = name;
        this.color = color;
        this.model = model;
    }

    public void turnOff() {
        System.out.println(this.name + " Turn off");
    }

    public void turnOn() {
        System.out.println(this.name + " Turn on");
    }

    public void setVolume() {
        System.out.println("Setting volume to " + this.name);
    }
}
