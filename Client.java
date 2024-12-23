import java.util.ArrayList;

/**
 * Client class
 */

public class Client {
    private Person person; //Delegation
    private ArrayList<String> messages;





    //constructor:
    public Client(String name, int balance, Person.Gender gender, String birthday) {
        this.person = new Person(name, balance, gender, birthday); //Delegation Initialized
    }


    public String getName(){
        return person.getName();
    }




}
