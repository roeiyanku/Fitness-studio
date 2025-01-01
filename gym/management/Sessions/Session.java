/**
 * Session class
 */

package gym.management.Sessions;
import gym.customers.Client;
import gym.management.Instructor;

import java.util.HashMap;
import java.util.Map;


public class Session {
    private String type;
   // private String dateandTime;
    private String time;
    private String date;
    private ForumType forumType; //Male, Female, Seniors or All
    private Instructor instructor;
    public static Map<Integer, Client> participants = new HashMap<>();
    private int maxParticipants;
    private int price;
    int sessionID;
    int counter = 0;
    int currentSizeParticipants;

    public Session(String type, int price, int maxParticipants, String date, String time, ForumType forumType, Instructor instructor){
     //   splitDateandTime(dateandTime);
     //  this.dateandTime = dateandTime;
        this.type = type;
        this.forumType = forumType;
        this.date = date;
        this.time = time;
        this.instructor = instructor;
        this.maxParticipants = maxParticipants;
        this.price = price;
        this.participants = new HashMap<>();
        this.sessionID = 2000 + counter;
        counter ++;
        currentSizeParticipants = 0;
    }


    // Getter methods

    public String getType() {
        return type;
    }

    public int getCurrentSizeParticipants(){ return participants.size(); }

   // public String getDateandTime() {
    //    return dateandTime;
  //  }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
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

    public void addParticipants(Client client){
        participants.put(client.getID(), client);
        currentSizeParticipants++;}

    public void removeParticipant(Client client){
        participants.remove(client.getID(), client);
        currentSizeParticipants--;
    }


 /*   public void splitDateandTime(String dateandtime){
        //split date and time
        String[] parts = dateandtime.split(" ");
        // calculate date
        this.date = parts[0];
        //take time
        this.time = parts[1];
    }
    */



//for example: gym.management.Sessions.Session Type: Pilates | Date: 23-01-2025 10:00 | Forum: All | gym.management.Instructor: Yuval | Participants: 1/30
    @Override
    public String toString() {
        return "gym.management.Sessions.Session Type: " + type + " | Date: " + date + " " + time + " | Forum: " + forumType + " | gym.management.Instructor: " + instructor.getName() +
                " | Participants: " + participants.size() + "/" + maxParticipants;
    }
}
