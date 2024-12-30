package gym.customers;

import gym.management.Sessions.Session;

import java.util.ArrayList;

/**
 * gym.customers.Client class
 */

public class Client extends Person implements NotificationObserver{

    private ArrayList<String> messages;
    private ArrayList<Session> sessions;


    public Client(Person person) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthday());
        this.messages = new ArrayList<>();
        this.sessions = new ArrayList<>();

    }
    public void addSession(Session session) {
        if (!sessions.contains(session)) {
            sessions.add(session);
        }
    }
    public void removeSession(Session session){
        if (sessions.contains(session)) {
            sessions.remove(session);
        }
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
       return  "ID: " + getID() + " | Name: " + getName() + " | gym.customers.Gender: " + getGender() +
                " | Birthday: " + getBirthday() + " | Age: " + calculateAge() +
                " | Balance: " + getBalance();
    }


}
