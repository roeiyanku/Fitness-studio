package gym.customers;
import gym.management.Gender;
import gym.management.Person;

import java.util.ArrayList;

/**
 * gym.customers.Client class
 */

public class Client extends Person implements NotificationObserver{

    private ArrayList<String> messages;


    public Client(Person person) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthday());
        ArrayList<String> messages = new ArrayList<>();
    }



 // get Notifications and add to the list
 public void getNotifications(String message) {
     messages.add(message);
 }

 //Print all messages the client has received
    public void printMessages() {
        System.out.println("Messages for " + getName() + ":");
        for (String msg : messages) {
            System.out.println(msg);
        }
    }

    @Override
    public String toString() {
       return  "ID: " + getID() + " | Name: " + getName() + " | gym.management.Gender: " + getGender() +
                " | Birthday: " + getBirthday() + " | Age: " + calculateAge() +
                " | Balance: " + getBalance();
    }



}
