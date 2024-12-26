/**
 * Session class
 */

package gym.management.Sessions;
import gym.customers.Client;
import gym.management.Instructor;

import java.util.ArrayList;


public class Session {
    private SessionType sessionType;
    private String date;
    private ForumType forumType; //Male, Female, Seniors or All
    private Instructor instructor;
    private ArrayList<Client> participants;
    private int maxParticipants;
    private int price;
    int sessionID;
    int counter = 0;



    // Getter methods
    public SessionType getSessionType() {
        return sessionType;
    }

    public String getDate() {
        return date;
    }

    public ForumType getForum() {
        return forumType;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public ArrayList<Client> getParticipants() {
        return participants;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public int getPrice() {
        return price;
    }

    public int getSessionID(){
        return sessionID;
    }

    public Session(int price, int capacity, String date, ForumType forumType, Instructor instructor){
        this.date = date;
        this.forumType = forumType;
        this.instructor = instructor;
        this.maxParticipants = capacity;
        this.price = price;
        this.sessionID = 2000 + counter;
        counter ++;
    }





//for example: gym.management.Sessions.Session Type: Pilates | Date: 23-01-2025 10:00 | Forum: All | gym.management.Instructor: Yuval | Participants: 1/30
    @Override
    public String toString() {
        return "gym.management.Sessions.Session Type: " + sessionType + " | Date: " + date + " | Forum: " + forum + " | gym.management.Instructor: " + instructor.getName() +
                " | Participants: " + participants.size() + "/" + maxParticipants;
    }
}
