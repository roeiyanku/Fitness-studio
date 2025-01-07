/**
 * Session class
 */

package gym.management.Sessions;
import gym.customers.Client;
import gym.management.Instructor;
import gym.management.Secretary;

import java.util.HashMap;
import java.util.Map;


public class Session {
    private String type; // Type of the session
    private String time;// Time of the session
    private String date;// Date of the session
    private ForumType forumType; //Male, Female, Seniors or All
    private Instructor instructor;// The instructor teaching the session
    private Map<Integer, Client> participants;// Participants enrolled in the session
    private int maxParticipants; // Maximum number of participants allowed in this session
    private int price; // Price of attending this session
    private int sessionID;// Unique session ID
    private static int counter = 0; // A counter to increment session IDs
    private int currentSizeParticipants; // Current number of participants in the session

    //Constructor to initialize a session with all necessary parameters.
    public Session(String type, int price, int maxParticipants, String date, String time, ForumType forumType, Instructor instructor){
        this.type = type;
        this.forumType = forumType;
        this.date = date;
        this.time = time;
        this.instructor = instructor;
        this.maxParticipants = maxParticipants;
        this.price = price;
        this.participants = new HashMap<>();
        this.sessionID = 2000 + counter;
        counter++;
        currentSizeParticipants = 0;
    }


    // Getter methods for session
    public String getType() {
        return type;
    }
    public int getCurrentSizeParticipants(){ return participants.size();}
    public String getDate() { return date; }
    public String getTime() { return time; }
    public ForumType getForum() { return forumType; }
    public Instructor getInstructor() { return instructor; }
    public Map<Integer, Client> getParticipants() {return new HashMap<>(participants);}
    public int getMaxParticipants() {
        return maxParticipants;
    }
    public int getPrice() {
        return price;
    }
    public int getSessionID(){
        return sessionID;
    }

    public void removeParticipants(Client client, Secretary secretary) {
        if (participants.containsKey(client.getID()) && secretary instanceof Secretary) {
            participants.remove(client.getID());
            currentSizeParticipants--;
    }}
    public void addParticipants(Client client, Secretary secretary) {
        if (!participants.containsKey(client.getID()) && secretary instanceof Secretary) {
            participants.put(client.getID(),client);
            currentSizeParticipants++;
        }}
    //Converts the session details to string
    @Override
    public String toString() {
        return "gym.management.Sessions.Session Type: " + type + " | Date: " + date + " " + time + " | Forum: " + forumType + " | gym.management.Instructor: " + instructor.getName() +
                " | Participants: " + participants.size() + "/" + maxParticipants;
    }
}
