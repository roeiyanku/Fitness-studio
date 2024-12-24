import java.util.ArrayList;

/**
 * Client class
 */

public class Client {
    private Person person;
    private ArrayList<String> messages = new ArrayList<>();



    public int getID(){
        return person.getID();
    }

    public String getName(){
        return person.getName();
    }


    private void getNotifications (){
    }


    @Override
    public String toString() {
       return  "ID: " + getID() + " | Name: " + getName() + " | Gender: " + person.getGender() +
                " | Birthday: " + person.getBirthday() + " | Age: " + person.calculateAge() +
                " | Balance: " + person.getBalance();
    }



}
