package gym.management;

import gym.customers.Client;
import gym.customers.Person;
import gym.management.Sessions.Session;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * gym.management.Gym class. There can only be one
 */


public class Gym {
    private static Gym instance;
    private String gymName;
    public static int gymBalance;
    private Secretary currentSecretary;
    public static List<String> actionsHistory = new ArrayList<>(); // actions History
    public static Map<Integer, Client> clients = new HashMap<>(); // Saving clients by ID
    public static Map<Integer, Session> sessions = new HashMap<>(); // Saving session by ID
    public static Map<Integer, Person> employees = new HashMap<>();


    // Constructor:
    private Gym (){

        this.gymName = null;
        this.gymBalance = 0;
        this.currentSecretary = null;
    }

    public static Gym getInstance(){
        if (instance == null){
            instance = new Gym();
        }
        return instance;
    }
    public ArrayList<Client> getClients() {
        return new ArrayList<>(clients);
    }

    public void setName(String name){
        this.gymName = name;
    }

    public Secretary getSecretary(){
        return currentSecretary;
    }

    public void setSecretary(Person person, int salary) {
        if (currentSecretary != null) {
            Gym.employees.remove(currentSecretary.getID(), currentSecretary);
            currentSecretary.setActive(False);
            Secretary newSecretary = new Secretary(person, salary);
            Gym.employees.put(newSecretary.getID(), newSecretary);

            newSecretary.setActive(True);
            actionsHistory.add("A new secretary has started working at the gym: " + newSecretary.getName() +"\n");
        }
        else {
            Secretary newSecretary = new Secretary(person, salary);
            Gym.employees.put(newSecretary.getID(), newSecretary);
            currentSecretary.setActive(True);
            actionsHistory.add("A new secretary has started working at the gym: " + newSecretary.getName() +"\n");
        }
    }
    public void setBalance(int balance){gymBalance = balance;}

    public int getBalance() {return gymBalance;}





    public String toString() {
        System.out.println("gym.management.Gym Name: " +gymName);
        System.out.println("gym.management.Gym gym.management.Secretary: " + gym.management.Secretary.toString());
        System.out.println("gym.management.Gym Balance: "+ getBalance());

        System.out.println();

        System.out.println("Clients Data:");
        clients.toString();
        System.out.println();


        System.out.println("Employees Data:");
        employees.toString();
        System.out.println();

        System.out.println("Sessions Data:");
        sessions.toString();







    }
}
