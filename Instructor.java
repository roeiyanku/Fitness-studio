/**
 * Instructor class
 */

public class Instructor {
    private Person person; //Delegation



    //constructor:
    public Instructor(String name, int balance, Person.Gender gender, String birthday) {
        this.person = new Person(name, balance, gender, birthday); //Delegation Initialized
    }




}