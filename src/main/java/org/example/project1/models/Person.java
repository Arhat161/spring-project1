package org.example.project1.models;

import org.example.project1.DAO.PersonDAO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Person {

    private int personId;
    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 2, max = 100, message = "ФИО должно быть от 2 до 100 символов")
    //@Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Your FIO should be in this format: Ivanov Ivan Ivanovich")
    private String personFio;
    @Min(value = 1900, message = "Год рождения должен быть больше, чем 1900")
    private int personYearBorn;
    public Person() {
    }
    public Person(String personFio, int personYearBorn) {
        this.personFio = personFio;
        this.personYearBorn = personYearBorn;
    }
    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPersonFio() {
        return personFio;
    }

    public void setPersonFio(String personFio) {
        this.personFio = personFio;
    }

    public int getPersonYearBorn() {
        return personYearBorn;
    }

    public void setPersonYearBorn(int personYearBorn) {
        this.personYearBorn = personYearBorn;
    }
}
