package gym.management;

import gym.customers.Client;
import gym.management.Secretary;

import java.util.ArrayList;

/**
 * gym.management.Gym class. There can only be one
 */


public class Gym {
    private static Gym instance;
    private ArrayList<Client> clients = new ArrayList<>();
    private String gymName;
    private Secretary currentSecretary;


    // Constructor:
    private Gym (){

        this.gymName = null;
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

}
