package model;

import annotation.Init;
import annotation.JsonElement;
import annotation.JsonSerializable;

@JsonSerializable
public class Person {

    @JsonElement
    private String firstName;

    @JsonElement
    private String lastName;

    @JsonElement(key = "personAge")
    private String age;

    private String address;

    public Person(String firstname, String lastName, String age) {
        this.firstName = firstname;
        this.lastName = lastName;
        this.age = age;
    }

    @Init
    private void initNames() {
        this.firstName = this.firstName.substring(0, 1).toUpperCase()
                + this.firstName.substring(1);
        this.lastName = this.lastName.substring(0, 1).toUpperCase()
                + this.lastName.substring(1);
    }

    // Standard getters and setters
}