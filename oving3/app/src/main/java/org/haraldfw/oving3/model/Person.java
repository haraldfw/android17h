package org.haraldfw.oving3.model;

public class Person {
    private String name;
    private String birthdate;

    public Person(String name, String birthdate) {
        this.birthdate = birthdate;
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + " " + birthdate;
    }
}
