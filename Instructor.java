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
        return String.format("ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %d | Role: %s | Salary per Hour: d% | Certified Classes: s%",
                id, person.getName(), person.getGender(), person.getBirthday(), person.calculateAge(), person.getBalance(), role , getSalaryPerHour(), certified_classes);
    }



}