package gym.customers;

import gym.management.Sessions.Session;

import java.util.ArrayList;

/**
 * gym.customers.Client class
 */

public class Client extends Person implements NotificationObserver{

    private ArrayList<String> messages;
    private ArrayList<Session> sessions;
    private boolean isDeleted;


    public Client(Person person) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthday());
        this.messages = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.isDeleted = false;
    }
    public boolean isDeleted() {
        return isDeleted;
    }
    public void deleteClient() {
        this.isDeleted = true;
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
    public String getNotifications() {
        return messages.toString();
    }

    public void addNotifications(String message) {
        messages.add(message);
    }

    @Override
    public String toString() {
       return  "ID: " + getID() + " | Name: " + getName() + " | gym.customers.Gender: " + getGender() +
                " | Birthday: " + getBirthday() + " | Age: " + calculateAge() +
                " | Balance: " + getBalance();
    }
}
