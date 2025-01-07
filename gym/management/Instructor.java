package gym.management;

import gym.customers.Person;
import java.util.ArrayList;

/**
 * The Instructor class represents a gym instructor who is also a person.
 * Instructors have a salary, a role, and a list of certified classes they can teach.
 * This class extends the Person class.
 */
public class Instructor extends Person{
    int salary; // The salary of the instructor
    String role = "gym.management.Instructor"; // The role of the instructor, set by default
    ArrayList<String> certified_classes;  // A list of classes the instructor is certified to teach

    /**
     * Constructor for creating an Instructor object.
     * It initializes the instructor's personal details, salary, and list of certified classes.
     */
    public Instructor(Person person, int salary, ArrayList listofClasses){
        // Call the constructor of the Person class to initialize personal details
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthday());
        this.salary = salary;
        this.certified_classes = listofClasses;
    }
    // Retrieves the list of certified classes the instructor can teach.
    public ArrayList<String> getcertified(){
        return certified_classes;
    }

    // Retrieves the salary of the instructor.
    public int getSalary(){
        return salary;
    }

    // Provides a detailed string representation of the instructor
    // return a string summarizing the instructor's details
    @Override
    public String toString() {
        return "ID: " + getID() + " | Name: " + getName() + " | gym.customers.Gender: " + getGender() +
                " | Birthday: " + getBirthday() + " | Age: " + calculateAge() +
                " | Balance: " + getBalance() + " | Role: " + role + " | Salary per Hour: " + getSalary() +
                " | Certified Classes: " + certified_classes;
    }
}