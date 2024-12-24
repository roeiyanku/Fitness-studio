/**
 * Session class
 */
import gym.management.ForumType;
import gym.management.SessionType;

import java.util.ArrayList;


public class Session {
    private SessionType sessionType;
    private String date;
    private ForumType forum; //Male, Female, Seniors or All
    private Instructor instructor;
    private ArrayList<Client> participants;
    private int maxParticipants;
    private int price;



    // Getter methods
    public SessionType getSessionType() {
        return sessionType;
    }

    public String getDate() {
        return date;
    }

    public ForumType getForum() {
        return forum;
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





//for example: Session Type: Pilates | Date: 23-01-2025 10:00 | Forum: All | Instructor: Yuval | Participants: 1/30
    @Override
    public String toString() {
        return "Session Type: " + sessionType + " | Date: " + date + " | Forum: " + forum + " | Instructor: " + instructor.getName() +
                " | Participants: " + participants.size() + "/" + maxParticipants;
    }
}
