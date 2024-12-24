import java.util.ArrayList;

/**
 * Client class
 */

public class Client {
    private Person person;
    int id;
    private ArrayList<String> messages;




    public String getName(){

        return person.getName();
    }

    @Override
    public String toString() {
        return String.format("ID: %d | Name: %s | Gender: %s | Birthday: %s | Age: %d | Balance: %d",
                id, getName(), person.getGender(), person.getBirthday(), person.calculateAge(), person.getBalance());
    }



}
