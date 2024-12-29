/**
 * Session class
 */

package gym.management.Sessions;
import gym.customers.Client;
import gym.management.Instructor;
import gym.management.Secretary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Session {
    private SessionType sessionType;
    private String date;
    private ForumType forumType; //Male, Female, Seniors or All
    private Instructor instructor;
    public static Map<Integer, Client> participants = new HashMap<>();
    private int maxParticipants;
    private int price;
    int sessionID;
    int counter = 0;
    int currentSizeParticipants;

    public Session(int price, int capacity, String date, ForumType forumType, Instructor instructor){
        this.date = date;
        this.forumType = forumType;
        this.instructor = instructor;
        this.maxParticipants = capacity;
        this.price = price;
        this.participants = new HashMap<>();
        this.sessionID = 2000 + counter;
        counter ++;
        currentSizeParticipants = 0;
    }


    // Getter methods

    public SessionType getSessionType() {
        return sessionType;
    }

    public int getCurrentSizeParticipants(){ return participants.size(); }


    public String getDate() {
        return date;
    }

    public ForumType getForum() {
        return forumType;
    }

    public Instructor getInstructor() {
        return instructor;
    }

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






//for example: gym.management.Sessions.Session Type: Pilates | Date: 23-01-2025 10:00 | Forum: All | gym.management.Instructor: Yuval | Participants: 1/30
    @Override
    public String toString() {
        return "gym.management.Sessions.Session Type: " + sessionType + " | Date: " + date + " | Forum: " + forumType + " | gym.management.Instructor: " + instructor.getName() +
                " | Participants: " + participants.size() + "/" + maxParticipants;
    }
}
