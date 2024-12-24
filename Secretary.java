import gym.Exception.*;
import gym.management.SessionType;

import java.util.ArrayList;

/**
 * Secretary class
 */



public class Secretary {
    private ArrayList<Client> clients;
    private ArrayList<String> actionsHistory;

    private Person person;
    private static Secretary instance ; //Singleton use
    int salary;
    String role = "Secretary";
    int id;



    public static Secretary getInstance(){
        if (instance == null){
            instance = new Secretary(person, salary);
        }
    }



    //setters and getters:

    public void setPerson(Person person) {
        this.person = person;
    }




    private void registerClient (Person person) throws InvalidAgeException, DuplicateClientException {
        if (person.calculateAge() < 18) {
            throw new InvalidAgeException();
        }

        for (Client existingClient : Gym.getClients()) {
            if (existingClient.getID() == person.getID()) {
                throw new DuplicateClientException();
            }}


        clients.add(newClient);

        String action = "Registered new client: " + person.getName();
        actionsHistory.add(action);

    }




    private void unregisterClient (Person person){
    }

    private void hireInstructor (Person person, int salary, ArrayList listofClasses){
    }

    private void addSession (SessionType sessionType, Date date , ForumType forumType, Instructor instructor){

            this.sessionType = sessionType;
            this.date = date;
            this.forum = forum;
            this.instructor = instructor;
            this.maxParticipants = maxParticipants;
            this.price = price;
            this.participants = new ArrayList<>();

    }

    private void registerClientToLesson (Client client, Session session){
    }

    private void paySalaries (){
    }

    private void printActions (){
    }





    public ArrayList<Client> getClients() {
        return clients;
    }


   //for example: ID: 1113 | Name: Maayan | Gender: Female | Birthday: 21-12-2005 | Age: 19 | Balance: 50 | Role: Secretary | Salary per Month: 8000
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + person.getName() + " | Gender: " + person.getGender() +
                " | Birthday: " + person.getBirthday() + " | Age: " + person.calculateAge() +
                " | Balance: " + person.getBalance() + " | Role: " + role + " | Salary per Month: " + salary;
    }
}






}