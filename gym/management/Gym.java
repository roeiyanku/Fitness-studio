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
            currentSecretary.setActive(False);
            Secretary newSecretary = new Secretary(person, salary);
            newSecretary.setActive(True);
        }
        else {
            Secretary newSecretary = new Secretary(person, salary);
            currentSecretary.setActive(True);

        }
    }
    public void addBalance(int m){
        if(m<0){ gymBalance += m;}}

    public int getBalance() {return gymBalance;}
}
