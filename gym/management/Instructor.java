package gym.management;

import gym.customers.Person;

import java.util.ArrayList;

/**
 * gym.management.Instructor class
 */

public class Instructor extends Person{
    int salary;
    String role = "gym.management.Instructor";
    ArrayList<String> certified_classes;

    public Instructor(Person person, int salary, ArrayList listofClasses){
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthday());
        this.salary = salary;
        this.certified_classes = listofClasses;
    }

    public ArrayList<String> getcertified(){
        return certified_classes;
    }

    public int getSalary(){
        return salary;
    }


    //example: ID: 1114 | Name: Shachar | gym.customers.Gender: Female | Birthday: 09-04-1958 | Age: 66 | Balance: 290 | Role: gym.management.Instructor | Salary per Hour: 70 | Certified Classes: ThaiBoxing, MachinePilates
    @Override
    public String toString() {
        return"ID: " + getID() + " | Name: " + getName() + " | gym.customers.Gender: " + getGender() +
                " | Birthday: " + getBirthday() + " | Age: " + calculateAge() +
                " | Balance: " + getBalance() + " | Role: " + role + " | Salary per Hour: " + getSalary() +
                " | Certified Classes: " + certified_classes;
    }



}