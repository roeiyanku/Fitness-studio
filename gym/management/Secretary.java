package gym.management;

import gym.Exception.*;
import gym.customers.*;
import gym.management.Sessions.ForumType;
import gym.management.Sessions.Session;
import gym.management.Sessions.SessionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import static gym.management.Sessions.SessionFactory.createSession;

/**
 * gym.management.Secretary class
 */



public class Secretary extends Person implements NotificationSubject {

    private Map<Integer, Client> clients = new HashMap<>(); // Saving clients by ID
    private Map<Integer, Session> sessions = new HashMap<>(); // Saving session by ID
    private List<String> actionsHistory = new ArrayList<>(); // actions History

    private static Secretary instance ; //Singleton use
    int salary;
    String role = "gym.management.Secretary";


    public Secretary(Person person, int salary){
        super(person.getName(), person.getBalance(), person.getGender(), person.getBirthday());
        this.salary = salary;

    }



    public static Secretary getInstance(){
        if (instance == null){
            instance = new Secretary(person, salary);
        }
    }




    //A message for all clients
    @Override
    public void notifyClient(String message) {
        for (Client client : clients.values()) {
            Client.getNotifications(message);
        }
        addAction("Sent notification to all clients: " + message);
    }

    //A message for all clients in Session
    public void notifyClientForSession(Session session, String message) {
            for (Client client :session.getParticipants()) {
                Client.getNotifications(message);
            }
            addAction("Sent notification to client for session: " + message);
        }


    public void notifyClientsForSessionAndDay(String day, Session session, String message) {
        Map<Session, List<NotificationObserver>> sessionMap = daySessionClients.get(day);
        if (sessionMap != null) {
            List<NotificationObserver> clientsForSession = sessionMap.get(session);
            if (clientsForSession != null) {
                for (NotificationObserver observer : clientsForSession) {
                    Client.getNotifications(message);
                }
            }
        }
    }




    private void registerClient(Person person) throws InvalidAgeException, DuplicateClientException {
        if (person.calculateAge() < 18) {
            throw new InvalidAgeException();
        }
        else{

            for (Client client : clients.values()) {
                if (client.getName().equals(person.getName())) {
                    throw new DuplicateClientException();
                }
            }
            Client newclient = new Client(person);
            clients.put(newclient.getID(), newclient);
            addAction("Registered new client: " + newclient.getName());
            return newclient;
        }
    }

    public void unregisterClient(Client client) throws ClientNotRegisteredException {
        if (!clients.containsKey(client.getID())) {
            throw new ClientNotRegisteredException();
        }
        clients.remove(client.getID());
        addAction("Unregistered client: " + client.getName());
    }

    public hireInstructor (Person person, int salary, List<SessionType> certifiedTypes){
        Instructor newInstructor = new Instructor(person, salary, certifiedTypes);
        addAction("Hired new instructor: " + person.getName() + " with hourly rate: " + salary);
        return newInstructor;
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







    public ArrayList<Client> getClients() {
        return clients;
    }

    public int getSalary(){
        return salary;
    }


   //for example: ID: 1113 | Name: Maayan | gym.management.Gender: Female | Birthday: 21-12-2005 | Age: 19 | Balance: 50 | Role: gym.management.Secretary | Salary per Month: 8000
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + getName() + " | gym.management.Gender: " + getGender() +
                " | Birthday: " + getBirthday() + " | Age: " + calculateAge() +
                " | Balance: " + getBalance() + " | Role: " + role + " | Salary per Month: " + getSalary();
    }
    private void addAction(String action) {
        actionsHistory.add(action);
    }

    public void printActions() {
        for (String action : actionsHistory) {
            System.out.println(action);
        }
    }

}
