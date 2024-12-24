import java.util.ArrayList;

/**
 * Instructor class
 */

public class Instructor{
    private Person person;
    int salary;
    String role = "Instructor";
    int id;
    ArrayList<String> certified_classes;

    public Instructor(){

    }


    //example: ID: 1114 | Name: Shachar | Gender: Female | Birthday: 09-04-1958 | Age: 66 | Balance: 290 | Role: Instructor | Salary per Hour: 70 | Certified Classes: ThaiBoxing, MachinePilates
    @Override
    public String toString() {
        return"ID: " + id + " | Name: " + person.getName() + " | Gender: " + person.getGender() +
                " | Birthday: " + person.getBirthday() + " | Age: " + person.calculateAge() +
                " | Balance: " + person.getBalance() + " | Role: " + role + " | Salary per Hour: " + getSalaryPerHour() +
                " | Certified Classes: " + certified_classes;
    }



}