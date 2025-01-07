package gym.customers;
import gym.management.Secretary;
import gym.management.Sessions.Session;
import java.util.ArrayList;

/**
 * gym.customers.Client class
 *  This class represents a client of the gym, extending from Person
 */

public class Client extends Person implements NotificationObserver{

    private ArrayList<String> messages;// List of messages (notifications) the client has received.
    private ArrayList<Session> sessions;  // List of sessions that the client is currently registered for.

    private boolean isDeleted;    // Flag indicating whether the client has been deleted from the gym.


    // Constructor that initializes the client object with the provided person's data,
    // and sets up empty lists for messages and sessions, while marking the client as not deleted by default.
    public Client(Person person) {
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthday());
        this.messages = new ArrayList<>();
        this.sessions = new ArrayList<>();
        this.isDeleted = false;
    }
    public boolean isDeleted() {
        return isDeleted;
    } // Returns whether the client is marked as deleted.

    public void getmessages(String message,Secretary secretary){
        if (secretary instanceof Secretary){
        messages.add(message);
    }}
    public void addSession(Session session, Secretary secretary) {
        if (!isDeleted && secretary instanceof Secretary){
            sessions.add(session);
        }}

    public void removeSession(Session session, Secretary secretary) {
        if (!isDeleted && secretary instanceof Secretary){
            sessions.remove(session);
        }}
    // Retrieves the list of notifications (messages) for the client.
    public String getNotifications() {
        return messages.toString();
    }

    // Provides a formatted string representation of the client's information
    @Override
    public String toString() {
       return  "ID: " + getID() + " | Name: " + getName() + " | gym.customers.Gender: " + getGender() +
                " | Birthday: " + getBirthday() + " | Age: " + calculateAge() +
                " | Balance: " + getBalance();
    }
}
