package com.example.bioapp.data;

public class Bio {
    private String name;
    private String hobbies;

    public Bio() {
    }

    public Bio(String name, String hobbies) {
        this.name = name;
        this.hobbies = hobbies;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String newHobbies) {
        this.hobbies = newHobbies;
    }
}
